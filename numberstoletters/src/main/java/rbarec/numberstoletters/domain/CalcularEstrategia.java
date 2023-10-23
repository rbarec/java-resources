package rbarec.numberstoletters.domain;

      
public class CalcularEstrategia { // NO_UCD (unused code)

	public void calcular(Palabra p) {
		CharsStats charStat = p.getCharStat();

		// DESDE AQUYI estrategia
		// ----------------
		// QUICKLY
		if (charStat.tieneLetrasAZaz()) {
			p.changeEstrategia(EstrategiaEnum.PALABRA);
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}

		if (charStat.tieneSimbolosNoDinero()) {
			p.changeEstrategia(EstrategiaEnum.SIMBOLOS_NO_DINERO);
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}

		if (charStat.tieneNumeros() && charStat.solamenteNumeros()) {
			if (p.getPalabra().length() == 10) {
				p.changeEstrategia(EstrategiaEnum.SOLO_NUMEROS_CEDULA10);
			} else if (p.getPalabra().length() == 13) {
				p.changeEstrategia(EstrategiaEnum.SOLO_NUMEROS_RUC_13);
			} else {
				p.changeEstrategia(EstrategiaEnum.SOLO_NUMERO_ENTERO);
			}
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}

		if (charStat.tieneNumeros() && //
				!charStat.tieneLetrasAZaz() && //
				!charStat.tieneSimbolosNoDinero() && charStat.tieneSimboloSeparadorDecimal()) {
			p.changeEstrategia(EstrategiaEnum.SOLO_NUMERO_CON_DECIMALES);
			p.changeEstado(EstadoPalabraEnum.ESTRATEGIA_FIN);
			return;
		}
	}

}
