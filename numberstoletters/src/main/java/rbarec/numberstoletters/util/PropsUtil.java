package rbarec.numberstoletters.util;

import java.util.Properties;

import rbarec.numberstoletters.domain.PropertiesEnum;
import rbarec.numberstoletters.exceptions.PropertiesException;

/**
 * 
 * @author Ronald
 *
 */
public class PropsUtil {

	/**
	 * TODO PENDIENTE validar el CAST
	 * 
	 * @param props
	 * @param key
	 * @return
	 * @throws PropertiesException
	 */
	public static boolean getBoolean(Properties props, PropertiesEnum key) throws PropertiesException {
		if (!PropertiesEnum.isBooleanType(key)) {
			throw new PropertiesException(
					"Error, La propiedad " + key.name() + " espera un valor de tipo " + key.getDataType());
		}
		Object obj = props.get(key.name());
		return obj==null?null:(Boolean) obj;
	}

	/**
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(Properties props, PropertiesEnum key, boolean defaultValue) {
		if (!PropertiesEnum.isBooleanType(key)) {
			return defaultValue;
		}
		try {
			Object obj = props.get(key.name());
			return obj==null?null:(Boolean) obj;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * TODO PENDIENTE validar el CAST
	 * 
	 * @param props
	 * @param key
	 * @return
	 * @throws PropertiesException
	 */
	public static String getString(Properties props, PropertiesEnum key) throws PropertiesException {
		if (!PropertiesEnum.isStringType(key)) {
			throw new PropertiesException(
					"Error, La propiedad " + key.name() + " espera un valor de tipo " + key.getDataType());
		}
		Object obj = props.get(key.name());
		return obj == null ? null : (String) obj;
	}
}
