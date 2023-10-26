package rbarec.numberstoletters.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import rbarec.numberstoletters.domain.EstadoPalabraEnum;
import rbarec.numberstoletters.domain.Palabra;
import rbarec.numberstoletters.domain.PalabraStats;
import rbarec.numberstoletters.domain.PropertiesEnum;
import rbarec.numberstoletters.domain.TipoEstrategiaEnum;
import rbarec.numberstoletters.dto.TransformaLetraResponse;
import rbarec.numberstoletters.dto.TransformaOracionResponse;
import rbarec.numberstoletters.exceptions.PropertiesException;
import rbarec.numberstoletters.transform.TransformarCedulaRuc;
import rbarec.numberstoletters.transform.TransformarPredio;
import rbarec.numberstoletters.transform.TransformarSimboloNumeroUnidos;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;
import rbarec.numberstoletters.util.PropsUtil;

/**
 * TransformarTextoSimple
 * 
 * @author Ronald
 *
 */
public class TransformarTextoSimple {
	private static final boolean WORD_WITH_ERROR_SAME_WORD = true;
	private boolean verboselog;

	/**
	 * 
	 * @param strTextoSimple
	 * @return
	 * @throws Exception
	 */
	public TransformaOracionResponse transformarTextoSimple(//
			String strTextoSimple, //
			Properties props//
	) throws Exception {

		// false es el valor por defecto en caso de error!
		this.verboselog = PropsUtil.getBoolean(props, PropertiesEnum.VERBOSE, false);
		//
		TransformaOracionResponse processResponse = new TransformaOracionResponse();

		// create array, split and clean empties!.
		processResponse.setArrPalabras(split_cleanBlancosTabsDeTexto(strTextoSimple));

		// Analisis de cada Palabra!
		procesarAnalisisPorCadaPalabra(processResponse);

		// RECORRO PALABRAS y ajusto los errores.
		for (Map.Entry<Integer, Palabra> entry : processResponse.getMapAnalisis().entrySet()) {
			Palabra val = entry.getValue();
			if (val.hasErrors() && WORD_WITH_ERROR_SAME_WORD) {
				// el arror obliga a devolver la palabra original
				val.fixResultsWithOriginalWordForTransform();
			}
		}

		// completar Palabras anterior y Posterior.
		completarPalabraConOtras(processResponse.getMapAnalisis(), processResponse.getArrPalabras(), props);
		
		for (Map.Entry<Integer, Palabra> entry : processResponse.getMapAnalisis().entrySet()) {
			
			Palabra palabra = entry.getValue();
			
			//CUANDO esta en MODO -NO_ESTRATEGY-
			if( palabra.getEstadoTransformacion()!= EstadoPalabraEnum.TRANSFORMACION_FIN &&
					palabra.isNoStrategy()
					) {
				TransformarSimboloNumeroUnidos ts = new TransformarSimboloNumeroUnidos();
				if(ts.validarNaturaleza(palabra)) {
					try {
						TransformaLetraResponse response = ts.transformar(palabra);
						palabra.recibirTransformacionEspecifica(response.getSalida(), response.getMsjErrors());
					} catch (Exception e) {
						e.printStackTrace();
						palabra.recibirTransformacionEspecifica(palabra.getPalabraInput(), e.getMessage());
					}
				}
				continue;
			}
			
			//CUANDO esta en MODO 
			//-SOLO_NUMERO_ENTERO-   
			//-SOLO_NUMERO_CON_DECIMALES-
			
		}

		// EJECUTO ESTRATEGIAS
		for (Map.Entry<Integer, Palabra> entry : processResponse.getMapAnalisis().entrySet()) {
			Palabra palabra = entry.getValue();
			
			if( palabra.getEstadoTransformacion()== EstadoPalabraEnum.TRANSFORMACION_FIN) {
				System.out.println("skip palabra: ya lista "+palabra.lightLog());
				continue;
			}
			//
			if (palabra.isEstrategiaNumericaFlag()) {
				ejecutarEstrategiaNumeros(palabra);
				continue;
			} else if (palabra.hasNoEstrategySelected()) {
				// AQUI PONER CUSTOM_ESTRATEGISA
				// PREDIO!!
				TransformarPredio transf = new TransformarPredio();
				if (transf.validarNaturaleza(palabra)) {
					TransformaLetraResponse t = transf.transformar(palabra);
					palabra.recibirTransformacionEspecifica(t.getSalida(), t.getMsjErrors());
					continue;
				}
				// default
				palabra.guardarResultadoTransformacion(palabra.palabraParaTransformar(), null);
				continue;
			} else {
				ejecutarEstrategiaPalabrasSimbolos_noNumeros(palabra);
				continue;
			}

		}

		for (Map.Entry<Integer, Palabra> entry : processResponse.getMapAnalisis().entrySet()) {
			Integer key = entry.getKey();
			Palabra val = entry.getValue();

			System.out.println("IMP::: " + key + "  " + val.lightLog() + "      ___R//" + val.getPalabraTransformada());
//			if (!EstrategiaEnum.PALABRA.equals(val.getEstrategia())
//					&& !EstrategiaEnum.OTRAS_PALABRAS.equals(val.getEstrategia())
//					&& !EstrategiaEnum.SIMBOLOS_NO_DINERO.equals(val.getEstrategia())) {
//				System.out.println("IMP::: " + key + "      " + val.lightLog() + "___R//" + val.getPalabraTransformada());
//			}
		}
		return processResponse;
	}

	/**
	 * completar Palabra Con Otras palabras anterior y posterior<br>
	 * Recorre el mapa de palabras
	 * 
	 * @param mapAnalisis
	 * @param arrPalabras
	 * @param props
	 * @throws PropertiesException
	 */
	private void completarPalabraConOtras(//
			Map<Integer, Palabra> mapAnalisis, List<String> arrPalabras, Properties props) throws PropertiesException {
		int boundary = mapAnalisis.size() - 1;

		for (Map.Entry<Integer, Palabra> entry : mapAnalisis.entrySet()) {
			Palabra palabra = entry.getValue();
			if (palabra.getOrden() == 0) {
				String prevWord = PropsUtil.getString(props, PropertiesEnum.PREVIUS_EXTRA_WORD);
				if (prevWord != null) {
					palabra.setTxtPreviousWord(prevWord);
				}
				palabra.setTxtNextWord(mapAnalisis.get(palabra.getOrden() + 1).getPalabraInput());
				System.out.println(palabra.getTxtPreviousWord() + "  " + palabra.getPalabraInput() + "  "
						+ palabra.getTxtNextWord());
				continue;
			}
			if (palabra.getOrden() == boundary) {
				palabra.setTxtPreviousWord(mapAnalisis.get(palabra.getOrden() - 1).getPalabraInput());
				palabra.setTxtNextWord(null);
				System.out.println(palabra.getTxtPreviousWord() + "  " + palabra.getPalabraInput() + "  "
						+ palabra.getTxtNextWord());
				continue;
			}
			palabra.setTxtPreviousWord(mapAnalisis.get(palabra.getOrden() - 1).getPalabraInput());
			palabra.setTxtNextWord(mapAnalisis.get(palabra.getOrden() + 1).getPalabraInput());
			System.out.println(
					palabra.getTxtPreviousWord() + "  " + palabra.getPalabraInput() + "  " + palabra.getTxtNextWord());
		}

	}

	/**
	 * <b>TITKE</b><br />
	 * 
	 * @throws Exception
	 */
	private void ejecutarEstrategiaPalabrasSimbolos_noNumeros(//
			Palabra anal //
	) throws Exception {
		if (anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.PALABRA) || //
				anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.OTRAS_PALABRAS) || //
				anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.SIMBOLOS_NO_DINERO) || //
				anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.ERROR_FIX_SAME_WORD)//
		) {
			anal.guardarResultadoTransformacion(anal.palabraParaTransformar(), null);
			return;
		}

		return;

	}

	/**
	 * <b>TITKE</b><br />
	 * 
	 * @throws Exception
	 */
	private void ejecutarEstrategiaNumeros(//
			Palabra anal //
	) throws Exception {

		NumberLetter_esUtil numberLetteresUtil = new NumberLetter_esUtil();
		TransformarCedulaRuc transf = new TransformarCedulaRuc();
		if (transf.validarNaturaleza(anal)) {
			TransformaLetraResponse t = transf.transformar(anal);
			anal.recibirTransformacionEspecifica(t.getSalida(), t.getMsjErrors());
		}

		if (anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.SOLO_NUMERO_ENTERO)) {
			String toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.numero, anal.palabraParaTransformar(), true);
			anal.guardarResultadoTransformacion(toLetras, null);
			return;
		}

		if (anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.SOLO_NUMERO_CON_DECIMALES)) {
			// TODO TRUCO PENDIENTE DE AJUSTAR
			String palabraTrucoChaoSeparadorDecimal = anal.palabraParaTransformar().replace(PalabraStats.MILES_SEPARADOR,
					"");
			
			palabraTrucoChaoSeparadorDecimal = retiraSignoDolarPrimerCaracter(palabraTrucoChaoSeparadorDecimal);
			String toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.numero, //
					palabraTrucoChaoSeparadorDecimal, //
					true);
			anal.guardarResultadoTransformacion(toLetras, null);
			return;
		}

		if (anal.getEstrategia().getTipoEnum().equals(TipoEstrategiaEnum.DINERO)) {
			String ajusteTrucoChaoSeparadorDecimal = anal.palabraParaTransformar().replace(PalabraStats.MILES_SEPARADOR,
					"");
			boolean separadorComa = true;
			String toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.dinero, ajusteTrucoChaoSeparadorDecimal, separadorComa);
			anal.guardarResultadoTransformacion(toLetras, null);
			return;
		}
		return;

	}

	private String retiraSignoDolarPrimerCaracter(String strPalabra) {
		if( strPalabra.charAt(0) == '$' ){
			return strPalabra.substring(1);
		}
		return strPalabra;
	}
	
	/**
	 * Procesar cada Palabra<br>
	 * AnalisisPalabraDTO su constructor hace analisis.<br>
	 * Estadistica Caracter a caracter<br>
	 * escoger la mejor estrategia<br>
	 * TODO PENDIENTE mover el calculo de la estrategia
	 * 
	 * @param to
	 * @param commons
	 */
	private void procesarAnalisisPorCadaPalabra(//
			TransformaOracionResponse to) {
		for (int indexWord = 0; indexWord < to.size(); indexWord++) {
			Palabra analisis = new Palabra(indexWord, to.getArrPalabras().get(indexWord));
			to.getMapAnalisis().put(indexWord, analisis);
		}
	}

	/**
	 * 
	 * @param text0Run
	 * @return
	 */
	private List<String> split_cleanBlancosTabsDeTexto(String text0Run) {
		text0Run = limpiarTabulaciones(text0Run);
		String[] arrPalabrasOriginales = text0Run.split(" ");
		List<String> arrPalabras = limpiandoEspaciosEnBlanco(arrPalabrasOriginales);//
		return arrPalabras;
	}

	/**
	 * reemplaza las tabulaciones de un texto.
	 * 
	 * @param text0Run
	 * @return
	 */
	private String limpiarTabulaciones(String text0Run) {
		return text0Run.replace("\t", " ");
	}

	/**
	 * Limpiar los espacios en blanco
	 * 
	 * @param arrOrigin
	 * @return
	 */
	private List<String> limpiandoEspaciosEnBlanco(String[] arrOrigin) {
		List<String> list = new ArrayList<>();
		for (int j = 0; j < arrOrigin.length; j++) {
			if (checkNotNullNotEmpty(arrOrigin[j])) {
				list.add(arrOrigin[j]);
			}
		}
		return list;
	}

	/**
	 * retorna TRUE si el objeto existe y no es nulo ni vacio.<br>
	 * hace trim()
	 * 
	 * @param reference
	 * @return
	 */
	private boolean checkNotNullNotEmpty(String reference) {
		if (reference == null) {
			return false;
		}
		if (reference.trim().isEmpty()) {
			return false;
		}
		return true;
	}
}
