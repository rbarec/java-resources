package rbarec.numberstoletters.util;

import rbarec.numberstoletters.exceptions.MoneyParseStringException;

/**
 * java.text MoneyConverter<br>
 * Convierte un string a bigdecimal. <br>
 * recibe un java.util.Locale;
 * 
 * @author Ronald
 *
 */
public class MoneyRegularExpresionUtil {
	/**
	 * Patron para saber si un texto es dinero
	 */
	private static final String PATTERN = "^[\\$]?[-+]?[\\d\\.,]*[\\.,]?\\d+$";

	public static void main(String[] args) throws MoneyParseStringException {

		System.out.println("Matches:(20) " + MoneyRegularExpresionUtil.isMoneyRegex("20")); // Should be true <br/>
		System.out.println("Matches:(20,00) " + MoneyRegularExpresionUtil.isMoneyRegex("20,00")); // Should be true<br/>
		System.out.println("Matches:(30.01) " + MoneyRegularExpresionUtil.isMoneyRegex("30.01")); // Should be true<br/>
		System.out.println("Matches:(30,000.01) " + MoneyRegularExpresionUtil.isMoneyRegex("30,000.01"));
		System.out.println("Matches:(-2980) " + MoneyRegularExpresionUtil.isMoneyRegex("-2980")); // Should be true<br/>
		System.out.println("Matches:($20) " + MoneyRegularExpresionUtil.isMoneyRegex("$20")); // Should be true<br/>
		System.out.println("Matches:(jdl) " + MoneyRegularExpresionUtil.isMoneyRegex("jdl")); // Should be false<br/>
		System.out.println("Matches:(2lk0) " + MoneyRegularExpresionUtil.isMoneyRegex("2lk0"));

		System.out.println("Matches:($20) " + MoneyRegularExpresionUtil.isMoneyRegex("$20")); // Should be true <br/>
		System.out.println("Matches:($20,00) " + MoneyRegularExpresionUtil.isMoneyRegex("$20,00")); // Should be
																									// true<br/>
		System.out.println("Matches:($30.01) " + MoneyRegularExpresionUtil.isMoneyRegex("$30.01")); // Should be
																									// true<br/>
		System.out.println("Matches:($30,000.01) " + MoneyRegularExpresionUtil.isMoneyRegex("$30,000.01"));
		System.out.println("Matches:($-2980) " + MoneyRegularExpresionUtil.isMoneyRegex("$-2980")); // Should be
																									// true<br/>
		System.out.println("Matches:($20) " + MoneyRegularExpresionUtil.isMoneyRegex("$20")); // Should be true<br/>

	}

	/**
	
	 */
	public static boolean isMoneyRegex(String stringVal) {
		if (stringVal.matches(PATTERN)) {
			return true;
		}
		return false;
	}
}
