package rbarec.numberstoletters.util;

import java.util.regex.Pattern;

import lombok.extern.log4j.Log4j2;
import rbarec.numberstoletters.exceptions.NumberLetterException;

/**
 * FUENTE: https://gist.github.com/leog1992/f96596d93b3ff4105ed7affa724f415f
 * 
 * @author Ronald Barrera
 */
@Log4j2
public class NumberLetter_esUtil {

	public static enum TipoEntradaEnum {
		numero, dinero, numero_entero
	};

	private final String[] UNIDADES = { "", "uno ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ",
			"nueve " };
	private final String[] DECENAS = { "diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
			"diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ", "cincuenta ", "sesenta ",
			"setenta ", "ochenta ", "noventa " };
	private final String[] CENTENAS = { "", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ",
			"seiscientos ", "setecientos ", "ochocientos ", "novecientos " };

	public NumberLetter_esUtil() {
	}

	/**
	 * No Lanza Exception por algun error, sino el mismo numero
	 * 
	 * @param tipo       TipoEntradaEnum.numero, TipoEntradaEnum.dinero
	 * @param numero     numero en String
	 * @param mayusculas si el resultado es en Mayusculas
	 * @return
	 * @throws CustomException
	 */
	public String convertir(//
			TipoEntradaEnum tipo, //
			String numero, //
			boolean mayusculas) throws NumberLetterException {

		if (TipoEntradaEnum.dinero == tipo) {
			return convertirDinero(tipo, numero, mayusculas);
		}

		if (!validarQueSeaNumeroDecimaloEntero(numero)) {
			return convertirNumeroAnumero(numero);
		}
		try {
			boolean tieneComaDecimal = tieneComaDecimal(numero);
			// se divide el numero 0000000,00 -> entero y decimal
			String splitNum[] = numero.split(",");
			String literal = "";
			String parte_decimal = "";
			if (tieneComaDecimal)
				parte_decimal = "y " + splitNum[1] + "/100"; //decimales

			// se convierte el numero a literal
			if (Integer.parseInt(splitNum[0]) == 0) {// si el valor es cero
				literal = "cero ";
			} else if (Integer.parseInt(splitNum[0]) > 999999) {// si es millon
				literal = getMillones(splitNum[0]);
			} else if (Integer.parseInt(splitNum[0]) > 999) {// si es miles
				literal = getMiles(splitNum[0]);
			} else if (Integer.parseInt(splitNum[0]) > 99) {// si es centena
				literal = getCentenas(splitNum[0]);
			} else if (Integer.parseInt(splitNum[0]) > 9) {// si es decena
				literal = getDecenas(splitNum[0]);
			} else {// sino unidades -> 9
				literal = getUnidades(splitNum[0]);
			}
			// devuelve el resultado en mayusculas o minusculas
			if (mayusculas) {
				return (literal + parte_decimal).toUpperCase();
			} else {
				return (literal + parte_decimal);
			}

		} catch (Exception e) {
			log.error("Error al convertir el numero ", e);
			e.printStackTrace();
			return numero;
		}
	}

	private String convertirNumeroAnumero(String numero) {
		String result = "";
		for (char ch : numero.toCharArray()) {
			if (Character.isDigit(ch)) {
				result += " " + ('0' == ch ? "cero" : getUnidades("" + ch));
			} else {
				if ('.' == ch)
					result += " PUNTO ";
				else if (',' == ch)
					result += " COMA ";
				else
					result += "" + ch;
			}

		}
		return result.toUpperCase();
	}

	public boolean validarQueSeaNumeroDecimaloEntero(String numero) {
		if (numero == null)
			return false;
		// se valida formato de entrada -> 0,00 y 999 999 999,00
		if (Pattern.matches("\\d{1,9},\\d{1,2}", numero) || Pattern.matches("\\d{1,9}", numero)) {
			return true;
		}
		return false;
	}

	/**
	 * Se espera un numero como estos: 25,32 444,44
	 * 
	 * @param tipo
	 * @param numero
	 * @param mayusculas
	 * @return
	 */
	public String convertirDineroEX(//
			TipoEntradaEnum tipo, //
			String numero, //
			boolean mayusculas) throws NumberLetterException {

		numero = siTieneComaYpuntoSimplificar(numero);

		if (!validarQueSeaNumeroDecimaloEntero(numero)) {

			try {
				throw new NumberLetterException("Validacion fallida. no es dinero valido " + numero + ".");
			} catch (NumberLetterException e) {
				e.printStackTrace();
			}
			return convertirNumeroAnumero(numero);

		}
		try {
			boolean tieneComaDecimal = tieneComaDecimal(numero);
			// se divide el numero 0000000,00 -> entero y decimal
			String splitNum[] = numero.split(",");
			String literal = "";
			String parte_decimal = "";
			if (tieneComaDecimal)
				parte_decimal = "y " + splitNum[1] + "/100 Dolares.";

			// se convierte el numero a literal
			if (Integer.parseInt(splitNum[0]) == 0) {// si el valor es cero
				literal = "cero ";
			} else if (Integer.parseInt(splitNum[0]) > 999999) {// si es millon
				literal = getMillones(splitNum[0]);
			} else if (Integer.parseInt(splitNum[0]) > 999) {// si es miles
				literal = getMiles(splitNum[0]);
			} else if (Integer.parseInt(splitNum[0]) > 99) {// si es centena
				literal = getCentenas(splitNum[0]);
			} else if (Integer.parseInt(splitNum[0]) > 9) {// si es decena
				literal = getDecenas(splitNum[0]);
			} else {// sino unidades -> 9
				literal = getUnidades(splitNum[0]);
			}
			// devuelve el resultado en mayusculas o minusculas
			if (mayusculas) {
				return (literal + parte_decimal).toUpperCase();
			} else {
				return (literal + parte_decimal);
			}

		} catch (Exception e) {
			log.error("Error al convertir el numero ", e);
			e.printStackTrace();
			return numero;
		}
	}

	private String siTieneComaYpuntoSimplificar(String numero) {
		if (numero.contains(".") && numero.contains(",")) {
			return numero = numero.replace(".", "");
		}
		if (numero.contains(".") && !numero.contains(",")) {
			return numero = numero.replace(".", ",");
		}
		return null;
	}

	public String convertirDinero(//
			TipoEntradaEnum tipo, //
			String numero, //
			boolean mayusculas) {
		try {
			return convertirDineroEX(tipo, numero, mayusculas);

		} catch (Exception e) {
			log.error("Error al convertir el numero " + numero + " como dinero", e);
			e.printStackTrace();
			return numero;
		}
	}

	private boolean tieneComaDecimal(String numero) {
		return numero.indexOf(",") != -1;
	}

	private long contarChars(String palabra, char michar) {
		return palabra.chars().filter(ch -> ch == michar).count();
	}

	/* funciones para convertir los numeros a literales */
	private String getUnidades(String numero) {// 1 - 9
		// si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
		String num = numero.substring(numero.length() - 1);
		return UNIDADES[Integer.parseInt(num)];
	}

	private String getDecenas(String num) {// 99
		int n = Integer.parseInt(num);
		if (n < 10) {// para casos como -> 01 - 09
			return getUnidades(num);
		} else if (n > 19) {// para 20...99
			String u = getUnidades(num);
			if (u.equals("")) { // para 20,30,40,50,60,70,80,90
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
			} else {
				return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
			}
		} else {// numeros entre 11 y 19
			return DECENAS[n - 10];
		}
	}

	private String getCentenas(String num) {// 999 o 099
		if (Integer.parseInt(num) > 99) {// es centena
			if (Integer.parseInt(num) == 100) {// caso especial
				return " cien ";
			} else {
				return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
			}
		} else {// por Ej. 099
			// se quita el 0 antes de convertir a decenas
			return getDecenas(Integer.parseInt(num) + "");
		}
	}

	private String getMiles(String numero) {// 999 999
		// obtiene las centenas
		String c = numero.substring(numero.length() - 3);
		// obtiene los miles
		String m = numero.substring(0, numero.length() - 3);
		String n = "";
		// se comprueba que miles tenga valor entero
		if (Integer.parseInt(m) > 0) {
			n = getCentenas(m);
			return n + "mil " + getCentenas(c);
		} else {
			return "" + getCentenas(c);
		}

	}

	private String getMillones(String numero) { // 000 000 000
		// se obtiene los miles
		String miles = numero.substring(numero.length() - 6);
		// se obtiene los millones
		String millon = numero.substring(0, numero.length() - 6);
		String n = "";
		if (millon.length() > 1) {
			n = getCentenas(millon) + "millones ";
		} else {
			n = getUnidades(millon) + "millon ";
		}
		return n + getMiles(miles);
	}
}