package rbarec.vo.controllers;

import lombok.Data;
import lombok.Getter;

/**
 * VALUE OBJECT - Cache Header Request <br>
 * El Campo cache debe tener entre 6 y 100 caracteres, además es alfanumerico
 * con guion, subguion y slash
 * 
 * @author Ronald
 * @since 15marzo
 */
@Data
@Getter
public class HeaderCacheVO {

	/**
	 * Holder del valor de cache
	 */
	private String cache;

	/**
	 * Nombre del Header,o KEY del header
	 */
	public static final String KEY_HEADER = "cache";
	public static final String NO_CACHE_VALUE = "no-cache";
	public static final String CACHE_VALUE = "cache";
	public static final String CONSTRAINS_TEXT = "El Campo cache debe ser cache o no-cache.";
	public static final String INFO_DESCRIPCION = " El Header cache tiene la etiqueta '" + KEY_HEADER + //
			"', los valores esperando son [" + CACHE_VALUE + "," + NO_CACHE_VALUE + "]";

	/**
	 * Cosntructor<br>
	 * * @param cacheHeaderValue NULL toma valor no-cache<br>
	 * - NPE_FREE libre de NullPointerException
	 */
	public HeaderCacheVO(String cacheHeaderValue) {
		super();
		this.cache = cacheHeaderValue == null ? NO_CACHE_VALUE : cacheHeaderValue;
	}

	/**
	 * Verifica si se requiere uso de cache para la API
	 * 
	 * @return
	 */
	public boolean requiereUsoCache() {
		if (cache == null || cache.isEmpty() || cache.trim().isEmpty())
			return false;
		if (CACHE_VALUE.equalsIgnoreCase(cache))
			return true;

		return false;
	}

	public String requiereUsarCache() {
		if (cache == null || cache.isEmpty() || cache.trim().isEmpty())
			return NO_CACHE_VALUE;
		if (CACHE_VALUE.equalsIgnoreCase(cache))
			return CACHE_VALUE;

		return NO_CACHE_VALUE;
	}

	@Override
	public String toString() {
		return "HeaderCacheVO [cache=" + cache + ", descripcion="+INFO_DESCRIPCION+"]";
	}

}
