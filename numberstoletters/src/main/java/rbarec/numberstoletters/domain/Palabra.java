package rbarec.numberstoletters.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
public class Palabra {
	/**
	 * Objeto Estrategia
	 */
	@Setter(AccessLevel.NONE)
	private Estrategia estrategia;

	private EstadoPalabraEnum estado;
	private String estadoLog;
	private String estadoMsj;
	private EstadoPalabraEnum estadoTransformacion;
	private String estadoTransformacionMsj;

	/**
	 * Orden de aparicion de palabra
	 */
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
	// @Getter(AccessLevel.NONE)
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
	private String primerCaracter;
	private String ultimoCaracter;
	private Boolean terminaSimbolo = false;
	private Boolean terminaSimboloPuntuacionGramatical = false;

	/**
	 * Estadistiscas de cada caracter de palabras
	 */
	private PalabraStats stats;

	/**
	 * Palabra anterior a la palabra actual
	 */
	private String txtPreviousWord;
	/**
	 * Palabra posterior a la palabra actual
	 */
	private String txtNextWord;

	/**
	 * 
	 * @param ordenIndex
	 * @param strPalabra
	 */
	public Palabra(int ordenIndex, String strPalabra) {
		super();
		this.orden = ordenIndex;
		this.palabraInput = strPalabra;
		estrategia = new Estrategia();
		// valor x defecto
		palabraParaTransformar = this.palabraInput; // xdefecto
		palabraClean = limpiarEspaciosyTabs(strPalabra);
		try {
			palabra = palabraClean;
			// ----------------
			// SIMBOLOS GRAMATICALES
			// char[] ch = new char[palabraClean.length()];
			primerCaracter = "" + palabraClean.charAt(0);
			ultimoCaracter = "" + palabraClean.charAt(palabraClean.length() - 1);
			log.debug("NOTA: Para analizar palabras debemos retirar los carcteres de ortografia como:"
					+ Constantes.GRAMATICAL_SIGN);
			// mayor_a_1 porque si viene coma sola se cae! y duplica!
			if (Constantes.RETIRAR_SIGNOS_PUNTUACION && palabra.length() >= 2) {
				if (Constantes.GRAMATICAL_SIGN.contains("" + ultimoCaracter)) {
					palabraSinPuntuacionGramatical = palabraClean.substring(0, palabraClean.length() - 1);
					palabra = palabraSinPuntuacionGramatical;
					mustAddLastSignal = true;
					terminaSimboloPuntuacionGramatical = true;
					tieneSimbolosPuntuacion = true;
				}
			}
			stats = new PalabraStats(palabra);
			palabraParaTransformar = palabra;
			CalcularEstrategia.calcular(this);
			//
		} catch (Exception e) {
			final String err = "ERROR Palabra (" + strPalabra + ") " + e.getMessage();
			log.error(err, e);
			e.printStackTrace();
			this.estado = EstadoPalabraEnum.ERROR;
			this.estadoMsj = err;
			this.porErrorSetPalabraFinalConDefault();
		}

		// ver si es un $ o USD y que el proximo token es CONVERTIBLE dinero!

	}

	/**
	 * obtener Palabra de trabajo Sin Primer Caracter<br>
	 * this.palabraParaTransformar
	 * 
	 * @return
	 */
	public String getPalabraSinPrimerCaracter() {
		return this.palabraParaTransformar.substring(1);
	}

	/**
	 * La palabra inicial se elimina TABS y espacios.
	 * 
	 * @param p
	 * @return
	 */
	private String limpiarEspaciosyTabs(String p) {
		estrategia.changeEstrategia(TipoEstrategiaEnum.NO_ESTRATEGY); // xdefecto
		if (p.contains("\t")) {
			p = p.replace("\t", "");
		}
		return p.trim();
	}

	// TODO viene del analisis de la palabra anterior.
	// TODO -sugerir_money- si anterior es $ es dinero, USD es dinero
	private String palabraAnteriorSugiereEstrategia;
	private String yoSugieroEstrategia;

	public void changeEstrategia(TipoEstrategiaEnum newEstrat) {
		estrategia.changeEstrategia(newEstrat);
	}

	public void changeEstado(EstadoPalabraEnum newEstadoEnum) {
		if (this.estado == null) {
			this.estadoLog = newEstadoEnum.name();
			this.estado = newEstadoEnum;
			return;
		}
		this.estadoLog = this.estadoLog + " - " + newEstadoEnum.name();
		this.estado = newEstadoEnum;
		return;
	}

	/**
	 * Quiero saber si la estrategia es numerica o no!<br>
	 * Hay una FLAG que almacena este valor!
	 * 
	 * @param estrategiaEnum
	 * @return
	 */
	public boolean esEstrategiaNumerica(TipoEstrategiaEnum estrategiaEnum) {
		return estrategia.getTipoEnum().isNumeric();
	}

	/**
	 * log simple de una linea palabra y estrategia
	 * 
	 * @return
	 */
	public String lightLog() {
		return " " + palabraInput + " [" + palabra + "] \t >" + estrategia + "<";
	}

	/**
	 * Para saber si hay errores en el objeto
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return EstadoPalabraEnum.ERROR.equals(this.estado);
	}

	/**
	 * usamos la palabra input para -palabraParaTransformar- <br>
	 * EstrategiaEnum.ERROR_FIX_SAME_WORD<br>
	 * EstadoEnum.TRANSFORMACION_FIN;<br>
	 */
	public void fixResultsWithOriginalWordForTransform() {
		this.porErrorSetPalabraFinalConDefault();
		estrategia.changeEstrategia(TipoEstrategiaEnum.ERROR_FIX_SAME_WORD);
		this.estado = EstadoPalabraEnum.TRANSFORMACION_FIN;
		this.estadoTransformacionMsj = "Error Estrategia and fixWithOriginalWord";

	}

	/**
	 * Tiene TIPO> NO-ESTRATEGY seleccionado.<br>
	 * Valida que tenga NO_ESTRATEGYdespues de analisis.
	 * 
	 * @return
	 */
	public boolean hasNoEstrategySelected() {
		return TipoEstrategiaEnum.NO_ESTRATEGY.equals(this.estrategia.getTipoEnum());
	}

	/**
	 * Bandera: es la estrategia definida en analisis Numerica
	 * 
	 * @return
	 */
	public boolean isEstrategiaNumericaFlag() {
		return estrategia.getTipoEnum().isNumeric();
	}

	/**
	 * Esta palabra tiene estrategia default NO-ESTRATEGY
	 * @return
	 */
	public boolean isNoStrategy() {
		return estrategia.isNoStrategy();
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
			estadoTransformacion = EstadoPalabraEnum.ERROR;
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
	public void guardarResultadoTransformacion(String wordTransformTo, String msjErrors) {
		wordTransformTo = wordTransformTo.trim();
		this.palabraTransformada = mustAddLastSignal ? wordTransformTo + ultimoCaracter : wordTransformTo;
		if (msjErrors != null) {
			estadoTransformacion = EstadoPalabraEnum.ERROR;
			estadoTransformacionMsj = msjErrors;

		}
	}

}
