package rbarec.numberstoletters.transform;

import rbarec.numberstoletters.dto.ErrorsInLoopDTO;
import rbarec.numberstoletters.domain.TipoEstrategiaEnum;
import rbarec.numberstoletters.domain.Palabra;
import rbarec.numberstoletters.dto.TransformaLetraResponse;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;

/**
 * Trasformar Cedula o RUC a Letras
 * 
 * @author Ronald
 *
 */
public class TransformarCedulaRuc implements TransformadorEspecificoPalabra {

	/**
	 * validar cedula o ruc
	 */
	@Override
	public boolean validarNaturaleza(Palabra analisis) {
		return analisis.getEstrategia().equals(TipoEstrategiaEnum.SOLO_NUMEROS_CEDULA10) || //
				analisis.getEstrategia().equals(TipoEstrategiaEnum.SOLO_NUMEROS_RUC_13);
	}

	/**
	 * transformar cedula o ruc
	 */
	public TransformaLetraResponse transformar(Palabra palabraObj) {
		String strPalabra = palabraObj.palabraParaTransformar();
		NumberLetter_esUtil numberLetteresUtil = new NumberLetter_esUtil();
		ErrorsInLoopDTO errors = ErrorsInLoopDTO.getInstance();

		StringBuilder res = new StringBuilder("");
		for (int j = 0; j < strPalabra.length(); j++) {
			try {
				String n = "" + strPalabra.charAt(j);
				// String toLetras = Transform_NumeroLetras.convertNumber(n);
				String toLetras = numberLetteresUtil.convertir(//
						TipoEntradaEnum.numero, n, true);
				res.append(toLetras).append(" ");
			} catch (Exception e) {
				e.printStackTrace();
				errors.addErrors("Error TransformarCedulaRuc. ").addErrors(e.getMessage());
				errors.addErrors("return same word.");
			}
		}
		TransformaLetraResponse response = TransformaLetraResponse.builder().entrada(strPalabra)//
				.salida(errors.hasErrors() ? strPalabra : res.toString())//
				.errors(errors.hasErrors()).msjErrors(errors.getErrors().toString())//
				.build();

		return response;
	}

}
