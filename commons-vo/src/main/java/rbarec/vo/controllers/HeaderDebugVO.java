package rbarec.vo.controllers;

import lombok.Data;
import lombok.Getter;

/**
 * HEADER DEBUG <br>
 * Toda la logica alrededor de el header debug<br>
 * El Campo debug debe traer 1, true, si para artivar el debug, caso contrario
 * <br>
 * 
 * @author Ronald
 */
@Data
@Getter
public class HeaderDebugVO {

	private boolean debug =true;
	private String debugParam;

	/**
	 * Palabra nombre del HEADER, en la peticion HTTP
	 */
	public static final String KEY_HEADER = "debug";
	public static final String VALUE_UNO = "1";
	public static final String VALUE_TRUE = "true";
	public static final String VALUE_SI = "si";
	public static final String CONSTRAINS_TEXT = "El Campo debug debe traer 1, true, si  " + //
			" para artivar el debug, caso contrario  ";

	/**
	 * Constructor <br>
	 * El Campo debug debe traer 1, true, si para artivar el debug, caso contrario
	 * <br>
	 * 
	 * @param debugHeaderValue NULL se hace false
	 */
	public HeaderDebugVO(String debugHeaderValue) {
		super();
		if (debugHeaderValue == null || debugHeaderValue.isEmpty() || debugHeaderValue.trim().isEmpty()) {
			debug = false;
			debugParam = "0";
			return ;
		}
		if (VALUE_UNO.equalsIgnoreCase(debugHeaderValue) || VALUE_TRUE.equalsIgnoreCase(debugHeaderValue)
				|| VALUE_SI.equalsIgnoreCase(debugHeaderValue)) {
			debugParam = "1";
			debug = true;
			return ;
		}
		debugParam = "1";
		debug = true;
	}
}
