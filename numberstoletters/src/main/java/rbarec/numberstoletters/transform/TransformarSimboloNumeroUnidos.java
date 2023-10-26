package rbarec.numberstoletters.transform;

import lombok.extern.log4j.Log4j2;
import rbarec.numberstoletters.domain.Palabra;
import rbarec.numberstoletters.dto.ErrorsInLoopDTO;
import rbarec.numberstoletters.dto.TransformaLetraResponse;
import rbarec.numberstoletters.exceptions.NumberLetterException;
import rbarec.numberstoletters.util.MoneyRegularExpresionUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;

/**
 * 
 * @author Ronald
 *
 */
@Log4j2
public class TransformarSimboloNumeroUnidos implements TransformadorEspecificoPalabra {

	// TEST
	// TEST
	public static void main(String[] args) {
		Palabra a = new Palabra(1, "$5000,00");
		System.out.println("Palabra:   "+a.lightLog());
		TransformarPredio tp = new TransformarPredio();
		System.out.println("Aplicable a TransformarSimboloNumeroUnidos? " + tp.validarNaturaleza(a));
		if (tp.validarNaturaleza(a)) {
			TransformaLetraResponse re = tp.transformar(a);
			System.out.println("aLetras= " + re);
		}
	}

	/**
	 * Verifica si cumple con los requisitos del caso.
	 */
	@Override
	public boolean validarNaturaleza(Palabra palabra) {
		return MoneyRegularExpresionUtil.isMoneyRegex(palabra.palabraParaTransformar());
	}

	/**
	 * 
	 */
	@Override
	public TransformaLetraResponse transformar(Palabra palabraObj) {
		String strPalabra = palabraObj.getPalabraSinPrimerCaracter();

		TransformaLetraResponse response = TransformaLetraResponse.builder().entrada(strPalabra).build();
		NumberLetter_esUtil numberLetteresUtil = new NumberLetter_esUtil();
		ErrorsInLoopDTO errors = ErrorsInLoopDTO.getInstance();

		String toLetras = null;

		try {
			toLetras = numberLetteresUtil.convertir(//
					TipoEntradaEnum.numero, strPalabra, true);

		} catch (NumberLetterException e) {
			log.error(e);
			errors.addErrors("Error TransformarPredio. ").addErrors(e.getMessage());
			errors.addErrors("return same word.");
		}

		response.setSalida(errors.hasErrors() ? strPalabra : toLetras);
		response.setErrors(errors.hasErrors());
		response.setMsjErrors(errors.getErrors().toString());

		return response;
	}

}
