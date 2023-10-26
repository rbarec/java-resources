package rbarec.numberstoletters.domain;

import java.util.Locale;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import rbarec.numberstoletters.exceptions.MoneyParseStringException;
import rbarec.numberstoletters.util.MoneyConverterLocale;
import rbarec.numberstoletters.util.MoneyRegularExpresionUtil;

/**
 * 
 * @author Ronald
 *
 */
@Log4j2
@Getter
@Setter
public class PalabraStats {
	/**
	 * 
	 */
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private final String palabra;

	// private static final String NumeroPatter =java.lang.Character.isDigit(ch[i])
	// private static String SimbolosConocidos = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	/**
	 * simbolosNoDinero
	 */
	private static final String simbolosNoDinero = "/*!@#%^&*()\"{}_[]|\\?/<>";
	/**
	 * simbolosDinero
	 */
	private static final String simbolosDinero = "$";
	/**
	 * simbolosSignosDinero
	 */
	private static final String simbolosSignosDinero = "+-";

	/**
	 * MILES_SEPARADOR
	 */
	public static final String MILES_SEPARADOR = ".";

	/**
	 * cuantos caracteres del alfabeto
	 */
	private int cuantosLetrasAZaz = 0;
	/**
	 * cuantos caracteres son numeros
	 */
	private int cuantosNumeros = 0;
	/**
	 * cuantos caracteres son simbolos
	 */
	private int cuantosSimbolos = 0;
	/**
	 * cuantos caracteres son simbolos que no representan dinero o decimales
	 */
	private int cuantosSimbolosNoDinero = 0;
	/**
	 * cuantos caracteres son simbolos de dinero o decimales
	 */
	private int cuantosSimbolosDinero = 0;
	/**
	 * Cuantos caracteres de signo dinero $ existen
	 */
	private int cuantosSimbolosSignoDinero = 0;
	/**
	 * cuantos caracteres son el separador decimal existen
	 */
	private int cuantosSimboloSeparadorDecimal = 0;
	/**
	 * suantos caracteres separadores de miles existen.
	 */
	private int cuantosSimboloSeparadorMiles = 0;

	/**
	 * Campo que dice si se logro con exito parsear a numero.
	 */
	private boolean parserToNumberSuccessful = false;

	/**
	 * constructor
	 * 
	 * @param strPalabra
	 */
	PalabraStats(String strPalabra) {
		super();

		char[] ch = new char[strPalabra.length()];
		for (int i = 0; i < strPalabra.length(); i++) {
			ch[i] = strPalabra.charAt(i);
			if (Character.isDigit(ch[i])) {
				cuantosNumeros++;
			} else if (Character.isLetter(ch[i])) {
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
				if (Constantes.DECIMALES_SEPARADOR.equalsIgnoreCase("" + ch[i])) {
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
				MoneyConverterLocale.parse(strPalabra, Locale.US);
				parserToNumberSuccessful = true ;
			} catch (MoneyParseStringException e) {
				log.error("Money parse error.", e);
			}
			if (!parserToNumberSuccessful && MoneyRegularExpresionUtil.isMoneyRegex(strPalabra)) {
				parserToNumberSuccessful = true;
			}
		}
		// ver si es un $ o USD y que el proximo token es CONVERTIBLE dinero!
		this.palabra = strPalabra;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneLetrasAZaz() {
		return cuantosLetrasAZaz > 0;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneNumeros() {
		return cuantosNumeros > 0;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneSimbolos() {
		return cuantosSimbolos > 0;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneSimbolosNoDinero() {
		return cuantosSimbolosNoDinero > 0;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneSimbolosDinero() {
		return cuantosSimbolosDinero > 0;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneSimbolosSignosDinero() {
		return cuantosSimbolosSignoDinero > 0;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean tieneSimboloSeparadorDecimal() {
		return cuantosSimboloSeparadorDecimal > 0;
	}

	/**
	 * 
	 * @return
	 */
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



}
