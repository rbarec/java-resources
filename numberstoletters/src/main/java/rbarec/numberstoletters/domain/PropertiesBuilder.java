package rbarec.numberstoletters.domain;

import java.util.Properties;

/**
 * Builder que solo acepta la lista especifica de Propiedades<br>
 * PropertiesEnum
 * 
 * @param key
 * @param value
 * @return
 */
public class PropertiesBuilder {

	/**
	 * Internal target object to fill.
	 */
	private Properties properties;

	/**
	 * static instance Builder
	 * 
	 * @return
	 */
	public static PropertiesBuilder builder() {
		PropertiesBuilder p = new PropertiesBuilder();
		p.properties = new Properties();
		return p;
	}

	/**
	 * constructor
	 */
	private PropertiesBuilder() {
		super();
	}

	/**
	 * Pone o agrega un objeto en el properties.<br>
	 * Donde la clave debe ser parte de -PropertiesEnum-
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public PropertiesBuilder put(PropertiesEnum key, Object value) {
		properties.put(key.name(), value);
		return this;
	}

	/**
	 * Exporta el resultado.
	 * 
	 * @return
	 */
	public Properties build() {
		return properties;
	}
}
