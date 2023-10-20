package rbarec.numberstoletters;

import rbarec.numberstoletters.dto.AcumErrorInLoop;
import rbarec.numberstoletters.dto.AnalisisPalabraDTO;
import rbarec.numberstoletters.dto.AnalisisPalabraDTO.EstrategiaEnum;
import rbarec.numberstoletters.dto.TransformaLetraResponse;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;

public class TransformarCedulaRuc implements TransformadorEspecificoPalabra {

	public TransformaLetraResponse transformar(String strCedula) {
		TransformaLetraResponse response = TransformaLetraResponse.builder().entrada(strCedula).build();
		NumberLetter_esUtil numberLetteresUtil = new NumberLetter_esUtil();
		AcumErrorInLoop errors = AcumErrorInLoop.getInstance();

		StringBuilder res = new StringBuilder("");
		for (int j = 0; j < strCedula.length(); j++) {
			try {
				String n = "" + strCedula.charAt(j);
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
		response.setSalida(errors.hasErrors() ? strCedula : res.toString());
		response.setErrors(errors.hasErrors());
		response.setMsjErrors(errors.getErrors().toString());

		return response;
	}

	@Override
	public boolean validarNaturaleza(AnalisisPalabraDTO analisis) {
		return analisis.getEstrategia().equals(EstrategiaEnum.SOLO_NUMEROS_CEDULA10) || //
				analisis.getEstrategia().equals(EstrategiaEnum.SOLO_NUMEROS_RUC_13);
	}
}
