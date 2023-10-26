package rbarec.numberstoletters.domain;

/**
 * Calcular Estrategia de una palabra
 * 
 * @author Ronald
 *
 */
public class CalcularEstrategia {
	/**
	 * Calcular estrategia basado en la estadistica de la palabra.
	 * 
	 * @param p
	 */
	public static void calcular(Palabra p) {
		PalabraStats stats = p.getStats();

		// Si la palabra contiene letras
		if (stats.tieneLetrasAZaz()) {
			p.changeEstrategia(TipoEstrategiaEnum.PALABRA);
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}

		// Si la palabra contiene simbolos de no dinero
		if (stats.tieneSimbolosNoDinero()) {
			p.changeEstrategia(TipoEstrategiaEnum.SIMBOLOS_NO_DINERO);
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}

		// Si la palabra contiene solo numeros.
		if (stats.tieneNumeros() && stats.solamenteNumeros()) {
			if (p.getPalabra().length() == 10) {
				p.changeEstrategia(TipoEstrategiaEnum.SOLO_NUMEROS_CEDULA10);
			} else if (p.getPalabra().length() == 13) {
				p.changeEstrategia(TipoEstrategiaEnum.SOLO_NUMEROS_RUC_13);
			} else {
				p.changeEstrategia(TipoEstrategiaEnum.SOLO_NUMERO_ENTERO);
			}
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}

		// Por ultimo! quedan los numeros decimales que pueden ser dinero.
		if (stats.tieneNumeros() && //
				!stats.tieneLetrasAZaz() && //
				!stats.tieneSimbolosNoDinero() && stats.tieneSimboloSeparadorDecimal()) {
			p.changeEstrategia(TipoEstrategiaEnum.SOLO_NUMERO_CON_DECIMALES);
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}
	}

}
