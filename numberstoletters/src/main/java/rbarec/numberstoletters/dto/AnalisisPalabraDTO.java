package rbarec.numberstoletters.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
public class AnalisisPalabraDTO {

	private boolean DEBEN_SIGNOS_PUNTUACION_RETIRADOS = true;

	/**
	 * Signos de Puntuacion gramaticas pegada al final de una palabra!
	 */
	private static String puntuacionGramaticalChars = "?,.:;";
	private static String SimbolosPuntuacionIniciales = "?,.-";
	private static final String LetrasAZazPattern = ".*[a-zA-Z].*";

	public static enum EstrategiaEnum {
		SIN_ESTRATEGIA, // no identifique que hacer!
		PALABRA, //
		OTRAS_PALABRAS, //
		SIMBOLOS_NO_DINERO, //
		ERROR_FIX_SAME_WORD, //
		SOLO_NUMEROS_CEDULA10, //
		SOLO_NUMEROS_RUC_13, //
		SOLO_NUMERO_ENTERO, //
		SOLO_NUMERO_CON_DECIMALES, //
		DINERO //

	}

	public static enum EstadoEnum {
		CREADO, //
		ESTRATEGIA_FIN, //
		TRANSFORMACION_FIN, //
		ERROR//
	}

	@Setter(AccessLevel.NONE)
	private EstrategiaEnum estrategia;
	private String estrategiaLog;
	private boolean estrategiaNumericaFlag;

	private EstadoEnum estado;
	private String estadoMsj;
	private EstadoEnum estadoTransformacion;
	private String estadoTransformacionMsj;

	@Setter(AccessLevel.NONE)
	private final int orden;

	/**
	 * palabra original que ingreso al constructor!
	 */
	@Setter(AccessLevel.NONE)
	private final String palabraInput;

	/**
	 * Palabras TEMPORAL sin espacion o tabs
	 */
	@Getter(AccessLevel.NONE)
	private String palabraClean;
	/**
	 * Palabras TEMPORAL que ignora los signos de Ortografia.
	 */
	@Getter(AccessLevel.NONE)
	private String palabraSinPuntuacionGramatical = null;
	/**
	 * Palabra con la que se trabaja todo constructor .
	 */
	@Getter(AccessLevel.NONE)
	private String palabra;

	/**
	 * Palabra o Palabras lista para transformacion.
	 */
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private String palabraParaTransformar;
	/**
	 * Resultado Final
	 */
	@Setter(AccessLevel.NONE)

	private String palabraTransformada;

	private Boolean tieneSimbolosPuntuacion;
	private Boolean mustAddLastSignal = false;
	private char primerSimbolo;
	private char ultimoSimbolo;
	private Boolean empiezaSimbolo = false;
	private Boolean terminaSimbolo = false;
	private Boolean terminaSimboloPuntuacionGramatical = false;

	private CharsStatsDTO charStat;

	public AnalisisPalabraDTO(int ordenIndex, String strPalabra) {
		super();
		this.orden = ordenIndex;
		this.palabraInput = strPalabra;
		changeEstrategia(EstrategiaEnum.SIN_ESTRATEGIA); // xdefecto
		// valor x defecto
		palabraParaTransformar = this.palabraInput; // xdefecto
		palabraClean = limpiarEspaciosyTabs(strPalabra);
		try {
			palabra = palabraClean;
			// ----------------
			// SIMBOLOS GRAMATICALES
			// char[] ch = new char[palabraClean.length()];
			primerSimbolo = palabraClean.charAt(0);
			ultimoSimbolo = palabraClean.charAt(palabraClean.length() - 1);
			log.debug("NOTA: Para analizar palabras debemos retirar los carcteres de ortografia como:"
					+ puntuacionGramaticalChars);
			// mayor_a_1 porque si viene coma sola se cae! y duplica!
			if (DEBEN_SIGNOS_PUNTUACION_RETIRADOS && palabra.length() >= 2) {
				if (puntuacionGramaticalChars.contains("" + ultimoSimbolo)) {
					palabraSinPuntuacionGramatical = palabraClean.substring(0, palabraClean.length() - 1);
					palabra = palabraSinPuntuacionGramatical;
					mustAddLastSignal = true;
					terminaSimboloPuntuacionGramatical = true;
					tieneSimbolosPuntuacion = true;
				}
			}
			charStat = new CharsStatsDTO(palabra);
			palabraParaTransformar = palabra;
			
			// DESDE AQUYI estrategia
			// ----------------
			// QUICKLY
			if (charStat.tieneLetrasAZaz()) {
				changeEstrategia(EstrategiaEnum.PALABRA);
				estado = EstadoEnum.ESTRATEGIA_FIN;
				return;
			}

			if (charStat.tieneSimbolosNoDinero()) {
				changeEstrategia(EstrategiaEnum.SIMBOLOS_NO_DINERO);
				estado = EstadoEnum.ESTRATEGIA_FIN;
				return;
			}

			if (charStat.tieneNumeros() && charStat.solamenteNumeros()) {
				if (palabra.length() == 10) {
					changeEstrategia(EstrategiaEnum.SOLO_NUMEROS_CEDULA10);
				} else if (palabra.length() == 13) {
					changeEstrategia(EstrategiaEnum.SOLO_NUMEROS_RUC_13);
				} else {
					changeEstrategia(EstrategiaEnum.SOLO_NUMERO_ENTERO);
				}
				estado = EstadoEnum.ESTRATEGIA_FIN;
				return;
			}

			if (charStat.tieneNumeros() && //
					!charStat.tieneLetrasAZaz() && //
					!charStat.tieneSimbolosNoDinero() && charStat.tieneSimboloSeparadorDecimal()) {
				changeEstrategia(EstrategiaEnum.SOLO_NUMERO_CON_DECIMALES);
				estado = EstadoEnum.ESTRATEGIA_FIN;
				return;
			}

			//
		} catch (Exception e) {
			final String err = "ERROR Palabra (" + strPalabra + ") " + e.getMessage();
			log.error(err, e);
			e.printStackTrace();
			this.estado = EstadoEnum.ERROR;
			this.estadoMsj = err;
			this.porErrorSetPalabraFinalConDefault();
		}

		// ver si es un $ o USD y que el proximo token es CONVERTIBLE dinero!

	}

	/**
	 * La palabra inicial se elimina TABS y espacios.
	 * 
	 * @param p
	 * @return
	 */
	private String limpiarEspaciosyTabs(String p) {
		changeEstrategia(EstrategiaEnum.SIN_ESTRATEGIA); // xdefecto
		if (p.contains("\t")) {
			p = p.replace("\t", "");
		}
		return p.trim();
	}

	// TODO viene del analisis de la palabra anterior.
	// TODO -sugerir_money- si anterior es $ es dinero, USD es dinero
	private String palabraAnteriorSugiereEstrategia;
	private String yoSugieroEstrategia;

	/**
	 * Unico Metodo de ajuste de estrategia
	 * 
	 * @param newEstrat
	 * @return
	 */
	private String changeEstrategia(EstrategiaEnum newEstrat) {
		this.estrategiaNumericaFlag = esEstrategiaNumerica(newEstrat);
		if (this.estrategia == null) {
			this.estrategiaLog = newEstrat.name();
			this.estrategia = newEstrat;
			return estrategiaLog;
		}
		this.estrategiaLog = this.estrategiaLog + " - " + newEstrat.name();
		this.estrategia = newEstrat;
		return this.estrategiaLog;
	}

	/**
	 * Unico Metodo de ajuste de estrategia EXTERNA
	 * 
	 * @param newEstrat
	 * @return
	 */
	public String changeEstrategiaExternal(EstrategiaEnum newEstrat) {
		this.estrategiaNumericaFlag = esEstrategiaNumerica(newEstrat);
		if (this.estrategia == null) {
			this.estrategiaLog = newEstrat.name();
			this.estrategia = newEstrat;
			return estrategiaLog;
		}
		this.estrategiaLog = this.estrategiaLog + " - (ex)" + newEstrat.name();
		this.estrategia = newEstrat;
		return this.estrategiaLog;
	}

	/**
	 * Quiero saber si la estrategia es numerica o no!<br>
	 * Hay una FLAG que almacena este valor!
	 * 
	 * @param n
	 * @return
	 */
	private boolean esEstrategiaNumerica(EstrategiaEnum n) {
		if (n.equals(EstrategiaEnum.DINERO) || //
				n.equals(EstrategiaEnum.SOLO_NUMERO_ENTERO) || //
				n.equals(EstrategiaEnum.SOLO_NUMERO_CON_DECIMALES) || //
				n.equals(EstrategiaEnum.SOLO_NUMEROS_RUC_13) || //
				n.equals(EstrategiaEnum.SOLO_NUMEROS_CEDULA10))
			return true;
		return false;
	}

	public String lightLog() {
		return " " + palabraInput + " [" + palabra + "] \t >" + estrategia + "<";
	}

	/**
	 * Para saber si hay errores en el objeto
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return EstadoEnum.ERROR.equals(this.estado);
	}

	/**
	 * usamos la palabra input para -palabraParaTransformar- <br>
	 * EstrategiaEnum.ERROR_FIX_SAME_WORD<br>
	 * EstadoEnum.TRANSFORMACION_FIN;<br>
	 */
	public void fixResultsWithOriginalWord_paraTransformar() {
		this.porErrorSetPalabraFinalConDefault();
		this.changeEstrategia(EstrategiaEnum.ERROR_FIX_SAME_WORD);
		this.estado = EstadoEnum.TRANSFORMACION_FIN;
		this.estadoTransformacionMsj = "Error Estrategia and fixWithOriginalWord";

	}

	/**
	 * Valida que tenga SIN-ESTRATEGIA despues de analisis.
	 * 
	 * @return
	 */
	public boolean sinEstrategia() {
		return EstrategiaEnum.SIN_ESTRATEGIA.equals(this.estrategia);
	}

	/**
	 * Palabra para ejecutar transformador numeroLetras<br>
	 * La unica forma de obtenerla<br>
	 * 
	 * @return
	 */
	public String palabraParaTransformar() {
		return palabraParaTransformar;
	}

	/**
	 * Cuando el algoritmo cae! x algun error debe poner el valor por defecto. la
	 * palabra que ingresa! por ejemplo. ademas de apagar cualquien transformacion
	 * con add last signal.<br>
	 * -usa: this.palabraInput<br>
	 * -mustAddLastSignal = false;<br>
	 * 
	 * @param defword
	 */
	private void porErrorSetPalabraFinalConDefault() {
		this.palabraParaTransformar = this.palabraInput;
		mustAddLastSignal = false;
	}

	/**
	 * Guardar valor de Transformacion a LETRAS<br>
	 * Para transformadores Especificos. Una Trasformador externo como Predio! hace
	 * el trabajo y envia el resultado.
	 * 
	 * @param salida
	 * @param msjErrors
	 */
	public void recibirTransformacionEspecifica(String wordTransformToNumber, String msjErrors) {
		this.palabraTransformada = wordTransformToNumber;
		// ADD ERROR DE TRANSFORMACION.
		if (msjErrors != null) {
			estadoTransformacion = EstadoEnum.ERROR;
			estadoTransformacionMsj = msjErrors;

		}
	}

	/**
	 * Guardar valor de Transformacion a LETRAS<br>
	 * Unico lugar de guardado.
	 * 
	 * @param wordTransformToNumber
	 * @param msjErrors
	 */
	public void guardarResultadoTransformacion(String wordTransformToNumber, String msjErrors) {
		wordTransformToNumber = wordTransformToNumber.trim();
		this.palabraTransformada = mustAddLastSignal ? wordTransformToNumber + ultimoSimbolo : wordTransformToNumber;
		if (msjErrors != null) {
			estadoTransformacion = EstadoEnum.ERROR;
			estadoTransformacionMsj = msjErrors;

		}
	}

}
