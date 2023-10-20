package rbarec.numberstoletters;

import rbarec.numberstoletters.dto.AnalisisPalabraDTO;

/**
 * 
 * <b>Analisis de Palabra</b><br />
 * Examina la palabra para saber<br />
 * que tipo ESTRATEGIA SE aplica<br />
 * NUMLET_MONEY<br>
 * NUMLET_ONLY_NUMBER<br>
 * NUMLET_NUMBRE_TO_NUMBER<br>
 * <br />
 */

public class AnalisisPalabraUtil  {

	/**
	 * 
	 * <b>Analisis de Palabra</b><br />
	 * Examina la palabra para saber<br />
	 * que tipo ESTRATEGIA SE aplica<br />
	 * NUMLET_MONEY NUMLET_ONLY_NUMBER NUMLET_NUMBRE_TO_NUMBER <br />
	 * <br />
	 */
	public AnalisisPalabraDTO executeAnalisisConTercerasPalabras(//
			AnalisisPalabraDTO analAnterior,
			AnalisisPalabraDTO anal,//
			boolean verbose) throws Exception {
		
			return anal;
	}



	private boolean soyPalabraAnteriorSugieroDinero(String palabraAnterior) {
		if (palabraAnterior.contains("$") || palabraAnterior.toUpperCase().contains("USD")) {
			return true;
		}
		return false;
	}

	

		
}
