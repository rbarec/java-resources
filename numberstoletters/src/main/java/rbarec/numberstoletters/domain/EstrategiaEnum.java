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
public enum EstrategiaEnum {
	NO_ESTRATEGY("", false), // no identifique que hacer!
	PALABRA("", false), //
	OTRAS_PALABRAS("", false), //
	SIMBOLOS_NO_DINERO("", false), //
	ERROR_FIX_SAME_WORD("", false), //
	SOLO_NUMEROS_CEDULA10("", true), //
	SOLO_NUMEROS_RUC_13("", true), //
	SOLO_NUMERO_ENTERO("", true), //
	SOLO_NUMERO_CON_DECIMALES("", true), //
	DINERO("", true);//

	private String description;
	/**
	 * Bandera que indica que una estrategia asignada es numerica.
	 */
	private boolean isNumeric;

	private EstrategiaEnum(String description, boolean isNumeric) {
		this.description = description;
		this.isNumeric = isNumeric;
	}

}
