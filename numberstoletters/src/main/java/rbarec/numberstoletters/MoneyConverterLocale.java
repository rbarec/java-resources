package rbarec.numberstoletters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import rbarec.numberstoletters.exceptions.MoneyParseStringException;

/**
 * java.text MoneyConverter<br>
 * Convierte un string a bigdecimal. <br>
 * recibe un java.util.Locale;
 * 
 * @author Ronald
 *
 */
public class MoneyConverterLocale {

	public static void main(String[] args) throws MoneyParseStringException {
		final String dollarsA = "$199.00";
		final String real = "R$ 399,00";
		final String dollarsB = "£25.00";
		final String tailingEuro = "90,83 €";
		final String dollarsC = "$199.00";
		final String dirham = "AED 449.00";

		System.out.println(parse(dollarsA, Locale.US));
		System.out.println(parse(real, Locale.FRANCE));
		System.out.println(parse(dollarsB, Locale.US));
		System.out.println(parse(tailingEuro, Locale.FRANCE));
		System.out.println(parse(dollarsC, Locale.US));
		System.out.println(parse(dollarsC, Locale.ENGLISH));
		System.out.println(parse(dollarsC, Locale.ROOT));
		System.out.println(parse(dirham, Locale.US));

	}

	/**
	 * Parseo, conversion a numero BigDecimal.
	 * Usa java.text.NumberFormat(Instance)
	 * @param amount
	 * @param locale
	 * @return
	 * @throws ParseException
	 */
	public static BigDecimal parse(final String amount, final Locale locale) throws MoneyParseStringException {
		final String MONEY_PARSE_PATTERN="[^\\d.,]";
		try {
			final NumberFormat numberFormatInstance = NumberFormat.getNumberInstance(locale);
			if (numberFormatInstance instanceof DecimalFormat) {
				((DecimalFormat) numberFormatInstance).setParseBigDecimal(true);
			}
			BigDecimal a = (BigDecimal) numberFormatInstance.parse(amount.replaceAll(MONEY_PARSE_PATTERN, ""));
			return a;
		} catch (ParseException e) {
			throw new MoneyParseStringException(e.getMessage());
		}
	}
}
