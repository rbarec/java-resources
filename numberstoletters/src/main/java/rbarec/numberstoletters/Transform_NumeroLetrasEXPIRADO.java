package rbarec.numberstoletters;

import java.math.BigDecimal;

public class Transform_NumeroLetrasEXPIRADO {

	/**
	 * SEPARADOR DECIMAL COMA
	 */
	public static final String SEPARADOR_DECIMAL_COMA = ","; // Europa continental y SudAmérica

	private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ",
			"OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS", "DIECISIETE",
			"DIECIOCHO", "DIECINUEVE", "VEINTE" };

	private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ", "SETENTA ",
			"OCHENTA ", "NOVENTA ", "CIEN " };

	private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ",
			"QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };

	public static String globalConversion(String valor) throws Exception {
		boolean ccoma = valor.contains(SEPARADOR_DECIMAL_COMA);
		int size0 = valor.length();
		if (ccoma) {
			String toLetras = Transform_NumeroLetrasEXPIRADO.convertTxtMoney(valor, ",");

			return Transform_NumeroLetrasEXPIRADO.convertTxtMoney(toLetras, ",");
		} else {
			if (size0 <= 3) {
				String toLetras = Transform_NumeroLetrasEXPIRADO.convertNumber(valor);
				return Transform_NumeroLetrasEXPIRADO.convertTxtMoney(toLetras, ".");
			} else {
				String toLetras = Transform_NumeroLetrasEXPIRADO.convertTxtMoney(valor + ".00", ".");
				return Transform_NumeroLetrasEXPIRADO.convertTxtMoney(toLetras, ".");
			}
		}
	}

	/**
	 * 
	 * <b>convertTxt_fromFormato</b><br />
	 * Caracteristica: <br />
	 * - <br />
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String convertNumberToLetter_NotariaFormato(String str, String separadorDecimal) throws Exception {

		if (SEPARADOR_DECIMAL_COMA.equalsIgnoreCase(separadorDecimal)) {
			return Transform_NumeroLetrasEXPIRADO.convertNumberToLetter(str);
		} else {
			// NORMALIZAMOS BORRANDO LAS COMAS y CONVIRTIENDO EL PUNTO EN COMA !
			boolean tienePuntos = str.contains(".");
			boolean tieneComas = str.contains(",");

			return str; // Transform_NumeroLetras.convertNumberToLetter(str);
		}
		// return Transform_NumeroLetras.convertNumber(str);
	}

	public static String convertTxtMoney(String str, String separadorDecimal) throws Exception {
		if (SEPARADOR_DECIMAL_COMA.equalsIgnoreCase(separadorDecimal)) {
			return Transform_NumeroLetrasEXPIRADO.convertNumberToLetter(str);
		} else {
			// NORMALIZAMOS BORRANDO LAS COMAS y CONVIRTIENDO EL PUNTO EN COMA !
			boolean tienePuntos = str.contains(".");
			boolean tieneComas = str.contains(",");

			return str; // Transform_NumeroLetras.convertNumberToLetter(str);
		}
	}

//	public static String normalizarNumeroAPuntoDecimal(String str) {
//		boolean tienePuntos = str.contains(".");
//		boolean tieneComas = str.contains(",");
//		char[] ch = new char[str.length()];
//		if(tienePuntos && tieneComas) {
//			str = str.replace(",",".");
//			int indexP= str.lastIndexOf(".");
//			
//			int i = 0;
//			for(int j = 0; j<str.length();j++) {
//				if( ".".equals(""+str.charAt(j))  ) {
//					if(j == indexP) {
//						ch[i++] = str.charAt(j);
//					}
//				}else {
//					ch[i++] = str.charAt(j);
//					
//				}
//			}
//			return new String(ch);
//		}else if(tieneComas) {
//			str = str.replace(",",".");
//			int indexP= str.lastIndexOf(".");
//			
//			int i = 0;
//			for(int j = 0; j<str.length();j++) {
//				if( ".".equals(""+str.charAt(j))  ) {
//					if(j == indexP) {
//						ch[i++] = str.charAt(j);
//					}
//				}else {
//					ch[i++] = str.charAt(j);
//					
//				}
//			}
//			return new String(ch);
//		}else {
//			return str;
//		}
//	}

	/**
	 * Convierte a letras un numero de la forma $123,456.32 (StoreMath)
	 * <p>
	 * Creation date 5/06/2006 - 10:20:52 AM
	 * 
	 * @param number Numero en representacion texto
	 * @return Numero en letras
	 * @since 1.0
	 */
	public static String convertNumberToLetter(final BigDecimal number) {
		return convertNumberToLetter(bigDecimalToString(number));
	}

	/**
	 * Convertimos el numero double a String, agregando formato para que sea
	 * procesado El numero de decimales esta determinado por %.2f ej. %10.2f (10
	 * posiciones enteras y 2 decimales) si no se pone el primer valor por default
	 * toma el valor entero completo
	 **/

	private static String bigDecimalToString(final BigDecimal numero) {
		;
		return String.format("%.2f", numero);
	}

	/**
	 * Convierte un numero en representacion numerica a uno en representacion de
	 * texto. El numero es valido si esta entre 0 y 999'999.999
	 * <p>
	 * Creation date 3/05/2006 - 05:37:47 PM
	 * 
	 * @param number Numero a convertir
	 * @return Numero convertido a texto
	 * @throws NumberFormatException Si el numero esta fuera del rango
	 * @since 1.0
	 */
	public static String convertNumberToLetter(final String number) throws NumberFormatException {
		String converted = new String();
		String splitNumber[] = number.replace('.', '#').split("#");
		if (splitNumber.length == 1) {
			splitNumber = number.replace(',', '#').split("#");
		}
		// Descompone el trio de millones - ¡SGT!
		int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 8))
				+ String.valueOf(getDigitAt(splitNumber[0], 7)) + String.valueOf(getDigitAt(splitNumber[0], 6)));
		if (millon == 1) {
			converted = "Un MILLON ";
		}
		if (millon > 1) {
			converted = convertNumber(String.valueOf(millon)) + "MILLONES ";
		}

		// Descompone el trio de miles - ¡SGT!
		int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 5))
				+ String.valueOf(getDigitAt(splitNumber[0], 4)) + String.valueOf(getDigitAt(splitNumber[0], 3)));
		if (miles == 1) {
			converted += "MIL ";
		}
		if (miles > 1) {
			converted += convertNumber(String.valueOf(miles)) + "MIL ";
		}

		// Descompone el ultimo trio de unidades - ¡SGT!
		int cientos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 2))
				+ String.valueOf(getDigitAt(splitNumber[0], 1)) + String.valueOf(getDigitAt(splitNumber[0], 0)));
		if (cientos == 1) {
			converted += "UN";
		}

		if (millon + miles + cientos == 0) {
			converted += "CERO";
		}
		if (cientos > 1) {
			converted += convertNumber(String.valueOf(cientos));
		}

		// Descompone los centavos
		System.out.println(splitNumber[1].toString());
		// --
		System.out.println(getDigitAt(splitNumber[1], 2));
		System.out.println(getDigitAt(splitNumber[1], 1));
		System.out.println(getDigitAt(splitNumber[1], 0));

		String x22 = String.valueOf(getDigitAt(splitNumber[1], 2))//
				+ String.valueOf(getDigitAt(splitNumber[1], 1)) + //
				String.valueOf(getDigitAt(splitNumber[1], 0));
		int centavos = Integer.parseInt(x22);
		if (centavos == 1) {
			converted += " CON 1/100";
		}
		if (centavos > 1) {
			converted += " CON " + centavos + "/100";
		}
		converted += " DOLARES";
		return converted;
	}

	/**
	 * Convierte los trios de numeros que componen las unidades, las decenas y las
	 * centenas del numero.
	 * <p>
	 * Creation date 3/05/2006 - 05:33:40 PM
	 * 
	 * @param number Numero a convetir en digitos
	 * @return Numero convertido en letras
	 * @since 1.0
	 */
	public static String convertNumber(final String number) {
		if (number.length() > 3) {
			throw new NumberFormatException("La longitud maxima debe ser 3 digitos");
		}

		String output = new String();
		if (getDigitAt(number, 2) != 0) {
			output = CENTENAS[getDigitAt(number, 2) - 1];
		}

		int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1)) + String.valueOf(getDigitAt(number, 0)));

		if (k <= 20) {
			output += UNIDADES[k];
		} else {
			if (k > 30 && getDigitAt(number, 0) != 0) {
				output += DECENAS[getDigitAt(number, 1) - 2] + "Y " + UNIDADES[getDigitAt(number, 0)];
			} else {
				output += DECENAS[getDigitAt(number, 1) - 2] + UNIDADES[getDigitAt(number, 0)];
			}
		}

		// Caso especial con el 100
		if (getDigitAt(number, 2) == 1 && k == 0) {
			output = "CIEN";
		}

		return output;
	}

	/**
	 * Retorna el digito numerico en la posicion indicada de derecha a izquierda
	 * <p>
	 * 
	 * Creation date 3/05/2006 - 05:26:03 PM
	 * 
	 * @param origin   Cadena en la cual se busca el digito
	 * @param position Posicion de derecha a izquierda a retornar
	 * @return Digito ubicado en la posicion indicada
	 * @since 1.0
	 */
	private static int getDigitAt(final String origin, final int position) {
		if (origin.length() > position && position >= 0) {
			return origin.charAt(origin.length() - position - 1) - 48;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {

		String valor = "234,56";
		System.out.println(Transform_NumeroLetrasEXPIRADO.globalConversion(valor));

//		
//		System.out.println(Transform_NumeroLetras.convertNumber("235"));
//		System.out.println(Transform_NumeroLetras.convertNumberToLetter("23000"));
//		
//		System.out.println(Transform_NumeroLetras.convertNumberToLetter("23.00"));
//		System.out.println(Transform_NumeroLetras.convertNumberToLetter("1023.00"));
//		System.out.println(Transform_NumeroLetras.convertNumberToLetter("100230.00"));
//		System.out.println(Transform_NumeroLetras.convertNumberToLetter("1777000.00"));

//		System.out.println(Transform_NumeroLetras.getDigitAt("2344",3));
//		System.out.println(Transform_NumeroLetras.getDigitAt("23",1));
//		System.out.println(Transform_NumeroLetras.getDigitAt("23",0));

//		System.out.println(Transform_NumeroLetras.convertTxtMoney("23.00","."));
//		System.out.println(Transform_NumeroLetras.convertTxtMoney("23.00","."));
//		System.out.println(Transform_NumeroLetras.convertTxtMoney("1023.00","."));
//		System.out.println(Transform_NumeroLetras.convertTxtMoney("100230,010","."));
//		System.out.println(Transform_NumeroLetras.convertTxtMoney("1777000,030","."));
//
//		System.out.println(Transform_NumeroLetras.convertTxtMoney("100230,50","."));
//		System.out.println(Transform_NumeroLetras.convertTxtMoney("1777000,50","."));
	}
}
