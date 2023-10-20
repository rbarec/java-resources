package rbarec.numberstoletters.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rbarec.numberstoletters.TransformarCedulaRuc;
import rbarec.numberstoletters.TransformarPredio;
import rbarec.numberstoletters.dto.AnalisisPalabraDTO;
import rbarec.numberstoletters.dto.AnalisisPalabraDTO.EstrategiaEnum;
import rbarec.numberstoletters.dto.CharsStatsDTO;
import rbarec.numberstoletters.dto.TransformaLetraResponse;
import rbarec.numberstoletters.dto.TransformaOracion;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;

public class TransformarTextoSimple {
	private static final boolean WORD_WITH_ERROR_SAME_WORD = true;
	private boolean verboselog;

	public static void main(String[] args) throws Exception {
		TransformarTextoSimple to = new TransformarTextoSimple();
		TransformaOracion x = 	to.transformarTextoSimple(1, "siendo $ 567,88"//
				+ " la $33.44 1 de 9 de 2023, suma USD $ 199.456,96. Registro de"//
				+ " $ 171.456,96. una en vir	tud  celular 0960590924", true);
		System.out.println( x.getTextoResultado() ); 
	}

	
	/**
	 * 
	 * @param z
	 * @param text0Run
	 * @param commons
	 * @return
	 * @throws Exception
	 */
	public TransformaOracion transformarTextoSimple(//
			int z, // Para saber si es el primer RUNS
			String text0Run, //
			boolean verboselog) throws Exception {

		this.verboselog = verboselog;
		TransformaOracion to = new TransformaOracion();
		to.setArrPalabras(split_cleanBlancosTabsDeTexto(text0Run));

		// Analisis de cada Palabra!
		procesarAnalisisPorCadaPalabra(to);

		for (Map.Entry<Integer, AnalisisPalabraDTO> entry : to.getMapAnalisis().entrySet()) {
			AnalisisPalabraDTO val = entry.getValue();
			if (val.hasErrors() && WORD_WITH_ERROR_SAME_WORD) {
				val.fixResultsWithOriginalWord_paraTransformar();
			}
		}

		for (Map.Entry<Integer, AnalisisPalabraDTO> entry : to.getMapAnalisis().entrySet()) {
			AnalisisPalabraDTO val = entry.getValue();
			//
			if (val.isEstrategiaNumericaFlag()) {
				ejecutarEstrategiaNumeros(val);
				continue;
			} else if (val.sinEstrategia()) {
				// AQUI PONER CUSTOM_ESTRATEGISA
				// PREDIO!!
				TransformarPredio transf = new TransformarPredio();
				if (transf.validarNaturaleza(val)) {
					TransformaLetraResponse t = transf.transformar(val.palabraParaTransformar());
					val.recibirTransformacionEspecifica(t.getSalida(), t.getMsjErrors());
					continue;
				}
				// default
				val.guardarResultadoTransformacion(val.palabraParaTransformar(), null);
				continue;
			} else {
				ejecutarEstrategiaPalabrasSimbolos_noNumeros(val);
				continue;
			}

		}

		for (Map.Entry<Integer, AnalisisPalabraDTO> entry : to.getMapAnalisis().entrySet()) {
			Integer key = entry.getKey();
			AnalisisPalabraDTO val = entry.getValue();

			System.out.println("IMP::: " + key + "  " + val.lightLog() + "      ___R//" + val.getPalabraTransformada());
//			if (!EstrategiaEnum.PALABRA.equals(val.getEstrategia())
//					&& !EstrategiaEnum.OTRAS_PALABRAS.equals(val.getEstrategia())
//					&& !EstrategiaEnum.SIMBOLOS_NO_DINERO.equals(val.getEstrategia())) {
//				System.out.println("IMP::: " + key + "      " + val.lightLog() + "___R//" + val.getPalabraTransformada());
//			}
		}
		return to;
	}

	/**
	 * <b>TITKE</b><br />
	 * 
	 * @throws Exception
	 */
	private void ejecutarEstrategiaPalabrasSimbolos_noNumeros(//
			AnalisisPalabraDTO anal //
	) throws Exception {
		if (anal.getEstrategia().equals(EstrategiaEnum.PALABRA) || //
				anal.getEstrategia().equals(EstrategiaEnum.OTRAS_PALABRAS) || //
				anal.getEstrategia().equals(EstrategiaEnum.SIMBOLOS_NO_DINERO) || //
				anal.getEstrategia().equals(EstrategiaEnum.ERROR_FIX_SAME_WORD)//
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
			AnalisisPalabraDTO anal //
	) throws Exception {

		NumberLetter_esUtil numberLetteresUtil = new NumberLetter_esUtil();
		TransformarCedulaRuc transf = new TransformarCedulaRuc();
		if (transf.validarNaturaleza(anal)) {
			TransformaLetraResponse t = transf.transformar(anal.palabraParaTransformar());
			anal.recibirTransformacionEspecifica(t.getSalida(), t.getMsjErrors());
		}

		if (anal.getEstrategia().equals(EstrategiaEnum.SOLO_NUMERO_ENTERO)) {
			String toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.numero, anal.palabraParaTransformar(), true);
			anal.guardarResultadoTransformacion(toLetras, null);
			return;
		}

		if (anal.getEstrategia().equals(EstrategiaEnum.SOLO_NUMERO_CON_DECIMALES)) {
			// TODO TRUCO PENDIENTE DE AJUSTAR
			String ajusteTrucoChaoSeparadorDecimal = anal.palabraParaTransformar()
					.replace(CharsStatsDTO.MILES_SEPARADOR, "");
			String toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.numero, //
					ajusteTrucoChaoSeparadorDecimal, //
					true);
			anal.guardarResultadoTransformacion(toLetras, null);
			return;
		}

		if (anal.getEstrategia().equals(EstrategiaEnum.DINERO)) {
			String ajusteTrucoChaoSeparadorDecimal = anal.palabraParaTransformar()
					.replace(CharsStatsDTO.MILES_SEPARADOR, "");
			boolean separadorComa = true;
			String toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.dinero, ajusteTrucoChaoSeparadorDecimal, separadorComa);
			anal.guardarResultadoTransformacion(toLetras, null);
			return;
		}
		return;

	}

	/**
	 * Procesar cada Palabra<br>
	 * AnalisisPalabraDTO su constructor hace analisis.
	 * 
	 * @param to
	 * @param commons
	 */
	private void procesarAnalisisPorCadaPalabra(//
			TransformaOracion to) {
		for (int indexWord = 0; indexWord < to.size(); indexWord++) {
			AnalisisPalabraDTO analisis = new AnalisisPalabraDTO(indexWord, to.getArrPalabras().get(indexWord));
			to.getMapAnalisis().put(indexWord, analisis);
		}
	}

	private List<String> split_cleanBlancosTabsDeTexto(String text0Run) {
		text0Run = limpiarTabulaciones(text0Run);
		String[] arrPalabrasOriginales = text0Run.split(" ");
		List<String> arrPalabras = limpiandoEspaciosEnBlanco(arrPalabrasOriginales);//
		return arrPalabras;
	}

	private String limpiarTabulaciones(String text0Run) {
		return text0Run.replace("\t", " ");
	}

	private List<String> limpiandoEspaciosEnBlanco(String[] arrOrigin) {
		List<String> list = new ArrayList<>();
		for (int j = 0; j < arrOrigin.length; j++) {
			if (verboselog)
				System.out.print("      --- arr[" + j + "]:  " + arrOrigin[j]);
			if (check_notNullNotEmpty(arrOrigin[j])) {
				list.add(arrOrigin[j]);
				if (verboselog)
					System.out.println("  OK  ");
			}
		}
		return list;
	}

	/**
	 * 
	 * @param reference
	 * @return
	 */
	private boolean check_notNullNotEmpty(String reference) {
		if (reference == null) {
			return false;
		}
		if (reference.trim().isEmpty()) {
			return false;
		}
		return true;
	}
}
