package rbarec.vo.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.Getter;

/**
 * https://www.baeldung.com/jackson-jsonformat
 * 
 * @author Ronald Patrones= "yyyy-MM-ddTHH:mm:ss.SSSZ"<br>
 *         Patrones= "yyyy-MM-ddTHH:mm:ss"<br>
 *         Patrones= "yyyy-MM-dd"<br>
 *
 */
@Data
@Getter
public class FechaJsonVO {

	public enum DateJsonPatternEnum {
		DATE_JSON_PATTERN_COMPLETA, //
		DATE_WITH_SECONDS_PATTERN, //
		DATE_NO_TIME_PATTERN
	}

	public static final String DATE_JSON_PATTERN = "yyyy-MM-ddTHH:mm:ss.SSSZ";
	public static final String DATE_WITH_SECONDS_PATTERN = "yyyy-MM-ddTHH:mm:ss";
	public static final String DATE_NO_TIME_PATTERN = "yyyy-MM-dd";

	private String strFecha;
	private Date fecha;

	/**
	 * pattern
	 * 
	 * @param strFecha
	 * @param pattern  when default is DATE_JSON_PATTERN= yyyy-MM-ddTHH:mm:ss.SSSZ
	 */
	public FechaJsonVO(String strFecha, DateJsonPatternEnum patternEnum) throws Exception {
		super();
		if (strFecha == null || strFecha.trim().isEmpty()) {
			throw new Exception("Texto Fecha es nula o vacia");
		}
		try {

			if (patternEnum.equals(FechaJsonVO.DateJsonPatternEnum.DATE_JSON_PATTERN_COMPLETA)) {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_JSON_PATTERN);
				this.fecha = sdf.parse(strFecha);
				this.setStrFecha(strFecha);
				this.setFecha(fecha);
			} else if (patternEnum.equals(FechaJsonVO.DateJsonPatternEnum.DATE_WITH_SECONDS_PATTERN)) {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_WITH_SECONDS_PATTERN);
				this.fecha = sdf.parse(strFecha);
				this.setStrFecha(strFecha);
				this.setFecha(fecha);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_NO_TIME_PATTERN);
				this.fecha = sdf.parse(strFecha);
				this.setStrFecha(strFecha);
				this.setFecha(fecha);
			}

		} catch (ParseException pe) {
			throw new Exception("Texto Fecha no se puede parsear.");
		}
		this.strFecha = strFecha;

	}

	public FechaJsonVO(Date fecha) {
		super();
		this.fecha = fecha;

	}

}
