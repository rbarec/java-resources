package rbarec.numberstoletters.dto;

import java.text.ParseException;
import java.util.Locale;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import rbarec.numberstoletters.MoneyConverterLocale;
import rbarec.numberstoletters.exceptions.MoneyParseStringException;

@Log4j2
@Getter
@Setter
public class CharsStatsDTO {

	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private final String palabra;

	// private static final String NumeroPatter =java.lang.Character.isDigit(ch[i])
	// private static String SimbolosConocidos = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	private static final String simbolosNoDinero = "/*!@#%^&*()\"{}_[]|\\?/<>";
	private static final String simbolosDinero = "$";
	private static final String simbolosSignosDinero = "+-";
	public static final String DECIMALES_SEPARADOR = ",";
	public static final String MILES_SEPARADOR = ".";

	private int cuantosLetrasAZaz = 0;
	private int cuantosNumeros = 0;
	private int cuantosSimbolos = 0;
	private int cuantosSimbolosNoDinero = 0;
	private int cuantosSimbolosDinero = 0;
	private int cuantosSimbolosSignoDinero = 0;
	private int cuantosSimboloSeparadorDecimal = 0;
	private int cuantosSimboloSeparadorMiles = 0;

	boolean parsearAnumero = false;

	public CharsStatsDTO(String palabra) {
		super();

		char[] ch = new char[palabra.length()];
		for (int i = 0; i < palabra.length(); i++) {
			ch[i] = palabra.charAt(i);
			if (java.lang.Character.isDigit(ch[i])) {
				cuantosNumeros++;
			} else if (java.lang.Character.isLetter(ch[i])) {
				cuantosLetrasAZaz++;
			} else {
				cuantosSimbolos++;
				if (simbolosNoDinero.contains("" + ch[i])) {
					cuantosSimbolosNoDinero++;
				}
				if (simbolosDinero.contains("" + ch[i])) {
					cuantosSimbolosDinero++;
				}
				if (simbolosSignosDinero.contains("" + ch[i])) {
					cuantosSimbolosSignoDinero++;
				}
				if (DECIMALES_SEPARADOR.equalsIgnoreCase("" + ch[i])) {
					cuantosSimboloSeparadorDecimal++;
				}
				if (MILES_SEPARADOR.equalsIgnoreCase("" + ch[i])) {
					cuantosSimboloSeparadorMiles++;
				}
			}
		} // fin for

		if (!tieneSimbolosNoDinero() && //
				!tieneLetrasAZaz() && //
				tieneNumeros()) {
			try {
				MoneyConverterLocale.parse(palabra, Locale.US);
				parsearAnumero = true;

			} catch (MoneyParseStringException e) {
				log.error("Money parse error.",e);
			}
			if (!parsearAnumero) {
				if (isMoneyRegex(palabra)) {
					parsearAnumero = true;
				}
			}
		}
		// ver si es un $ o USD y que el proximo token es CONVERTIBLE dinero!
		this.palabra = palabra;
	}

	public Boolean tieneLetrasAZaz() {
		return cuantosLetrasAZaz > 0;
	}

	public Boolean tieneNumeros() {
		return cuantosNumeros > 0;
	}

	public Boolean tieneSimbolos() {
		return cuantosSimbolos > 0;
	}

	public Boolean tieneSimbolosNoDinero() {
		return cuantosSimbolosNoDinero > 0;
	}

	public Boolean tieneSimbolosDinero() {
		return cuantosSimbolosDinero > 0;
	}

	public Boolean tieneSimbolosSignosDinero() {
		return cuantosSimbolosSignoDinero > 0;
	}

	public Boolean tieneSimboloSeparadorDecimal() {
		return cuantosSimboloSeparadorDecimal > 0;
	}

	public Boolean tieneSimboloSeparadorMiles() {
		return cuantosSimboloSeparadorMiles > 0;
	}

	/**
	 * Unicamente Numeros en absoluto.
	 * 
	 * @return
	 */
	public Boolean solamenteNumeros() {
		return tieneNumeros() && !tieneLetrasAZaz() && !tieneSimbolos();
	}

	/**
	   ("Matches:(20) "+MoneyUtil.isMoney("20"));         // Should be true <br/>
	     ("Matches:(20,00) "+MoneyUtil.isMoney("20,00"));          // Should be true<br/>
	     ("Matches:(30.01) "+MoneyUtil.isMoney("30.01"));          // Should be true<br/>
	     ("Matches:(30,000.01) "+MoneyUtil.isMoney("30,000.01"));          // Should be true<br/>
	     ("Matches:(-2980) "+MoneyUtil.isMoney("-2980"));          // Should be true<br/>
	     ("Matches:($20) "+MoneyUtil.isMoney("$20"));            // Should be true<br/>
	     ("Matches:(jdl) "+MoneyUtil.isMoney("jdl"));            // Should be false<br/>
	     ("Matches:(2lk0) "+MoneyUtil.isMoney("2lk0")); 
	 */
	public boolean isMoneyRegex(String stringVal) {
	    if (stringVal.matches("^[\\$]?[-+]?[\\d\\.,]*[\\.,]?\\d+$")) {
	        return true;
	    }

	    return false;
	}
	
}
