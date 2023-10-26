package rbarec.numberstoletters.domain;

import lombok.Getter;

/**
 * Todas las properties que puede recibir el sistema
 * 
 * @author Ronald
 *
 */
@Getter
public enum PropertiesEnum {


	/**
	 * true,false. activa logs, se retorna logs del proceso en el response.
	 */
	VERBOSE("boolean"), //
	/**
	 * palabra que debe analizarse y sugerir a la primera palabra "// + "de la
	 * oracion-palabra en proceso.
	 */
	PREVIUS_EXTRA_WORD("string");
	
	/**
	 * Los tipos de datos en minuscula. <br>
	 * boolean, string
	 */
	private String dataType;

	private PropertiesEnum(String dataType) {
		this.dataType = dataType;
	}

	public static boolean isBooleanType(PropertiesEnum propTypeEnum) {
		return propTypeEnum.dataType == "boolean";
	}
	public static boolean isStringType(PropertiesEnum propTypeEnum) {
		return propTypeEnum.dataType == "string";
	}
}
