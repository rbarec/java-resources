package rbarec.springboot3zero;

import lombok.extern.log4j.Log4j2;
import rbarec.numberstoletters.exceptions.NumberLetterException;
import rbarec.numberstoletters.util.NumberLetter_esUtil;
import rbarec.numberstoletters.util.NumberLetter_esUtil.TipoEntradaEnum;

/**
 * FUENTE: https://gist.github.com/leog1992/f96596d93b3ff4105ed7affa724f415f
 * 
 * @author Ronald Barrera
 */
@Log4j2
public class NumberLetter_esUtil_TEST {

	

	public static void main(String[] args) throws NumberLetterException {
		NumberLetter_esUtil letritas = new NumberLetter_esUtil();

		System.out.println(letritas.validarQueSeaNumeroDecimaloEntero("199456,96"));
		System.out.println(letritas.validarQueSeaNumeroDecimaloEntero("9199456"));
		System.out.println(letritas.validarQueSeaNumeroDecimaloEntero("0922067715"));
		try {
			System.out.println(letritas.convertirDineroEX(TipoEntradaEnum.dinero, "199456,96", true));
			System.out.println(letritas.convertirDineroEX(TipoEntradaEnum.dinero, "9199456,96", true));
		} catch (NumberLetterException e) {
			e.printStackTrace();
		}
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "092291994506", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "0922919945001", true));

		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "092291994506", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "0922919945001", true));

		String result = "";

		System.out.println(letritas.convertir(TipoEntradaEnum.dinero, "199456,96", true));

		System.out.println(letritas.convertir(TipoEntradaEnum.numero_entero, "922067715", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero_entero, "1212121", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero_entero, "235235235", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero_entero, "23000", true));

		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "235", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "23000", true));

		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "23,00", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "1023,00", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "100230,00", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "1777000,00", true));

		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "23,00", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "23,00", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "1023,00", true));

		System.out.println("---");
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "100230,01", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "1777000,03", true));

		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "100230,50", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "921777000,6", true));
		System.out.println(letritas.convertir(TipoEntradaEnum.numero, "1768", true));

		System.out.print(result);
	}

	
}