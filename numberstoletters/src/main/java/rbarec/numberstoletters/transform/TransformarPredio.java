package rbarec.numberstoletters.transform;

import lombok.extern.log4j.Log4j2;
import rbarec.numberstoletters.domain.Palabra;
import rbarec.numberstoletters.dto.ErrorsInLoopDTO;
import rbarec.numberstoletters.dto.TransformaLetraResponse;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;

@Log4j2
public class TransformarPredio implements TransformadorEspecificoPalabra {

	private final String SEPARADOR_PREDIO = "-";

	// TEST
	// TEST
	public static void main(String[] args) {
		Palabra a = new Palabra(1, "10-10-10");
		TransformarPredio tp = new TransformarPredio();
		System.out.println(tp.validarNaturaleza(a));
		if (tp.validarNaturaleza(a)) {
			TransformaLetraResponse re = tp.transformar(a);
			System.out.println(re);
		}

	}

	@Override
	public boolean validarNaturaleza(Palabra analisis) {
		if (!analisis.palabraParaTransformar().contains(SEPARADOR_PREDIO))
			return false;
		String[] arr = analisis.palabraParaTransformar().split(SEPARADOR_PREDIO);
		if (arr.length > 2) {
			return true;
		}

		return false;
	}

	@Override
	public TransformaLetraResponse transformar(Palabra palabraObj) {
		String strPalabra = palabraObj.palabraParaTransformar();
		TransformaLetraResponse response = TransformaLetraResponse.builder().entrada(strPalabra).build();
		NumberLetter_esUtil numberLetteresUtil = new NumberLetter_esUtil();
		ErrorsInLoopDTO errors = ErrorsInLoopDTO.getInstance();

		StringBuilder res = new StringBuilder("");
		String[] arr = strPalabra.split(SEPARADOR_PREDIO);
		for (int j = 0; j < arr.length; j++) {
			boolean isLastItem = j == arr.length - 1;
			String palabra = "" + arr[j];
			Palabra dto = new Palabra(j, palabra);
			String toLetras = palabra;
			try {
				if (dto.isEstrategiaNumericaFlag()) {
					toLetras = numberLetteresUtil.convertir(//
							TipoEntradaEnum.numero, palabra, true);
				}
			} catch (Exception e) {
				log.error(e);
				errors.addErrors("Error TransformarPredio. ").addErrors(e.getMessage());
				errors.addErrors("return same word.");
			}
			res.append(toLetras == null ? palabra : toLetras.trim());
			if (!isLastItem) {
				res.append("" + SEPARADOR_PREDIO + "");
			}

		}
		response.setSalida(errors.hasErrors() ? strPalabra : res.toString());
		response.setErrors(errors.hasErrors());
		response.setMsjErrors(errors.getErrors().toString());

		return response;
	}

}
