package rbarec.numberstoletters.domain;

import lombok.Getter;

/**
 * Enumeration used to tell the PALABRA the type of estrategy to execute.
 * <ul>
 * <li>NO_ESTRATEGY - default estrategy.</li>
 * 
 * </ul>
 */
@Getter
public enum TipoEstrategiaEnum {
	/**
	 * Palabra que no se identifica una estrategia.
	 */
	NO_ESTRATEGY(false),
	/**
	 * Palabra que tiene caracteres alfabeticos.
	 */
	PALABRA(false),
	/**
	 * Palabra que tiene mezcla de signos y caracteres alfabeticos
	 */
	OTRAS_PALABRAS(false),
	/**
	 * Palabra que tiene mezcla de signos y caracteres alfabeticos
	 */
	SIMBOLOS_NO_DINERO(false),
	/**
	 * Palabra que
	 */
	ERROR_FIX_SAME_WORD(false),
	/**
	 * Palabra que
	 */
	SOLO_NUMEROS_CEDULA10(true),
	/**
	 * Palabra que
	 */
	SOLO_NUMEROS_RUC_13(true),
	/**
	 * Palabra que
	 */
	SOLO_NUMERO_ENTERO(true),
	/**
	 * Palabra que
	 */
	SOLO_NUMERO_CON_DECIMALES(true),
	/**
	 * Palabra que
	 */
	DINERO(true);

	/**
	 * Bandera que indica que una estrategia asignada es numerica.
	 */
	private boolean isNumeric;

	/**
	 * constructor
	 */
	private TipoEstrategiaEnum(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

}
