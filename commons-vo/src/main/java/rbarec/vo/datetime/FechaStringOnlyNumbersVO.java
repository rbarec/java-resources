package rbarec.vo.datetime;

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
public class FechaStringOnlyNumbersVO {
	
	private String strFecha;
	
	public enum DatePatternEnum {
		PATTERN_ONLY_NUMER_DEFAULT("yyyyMMddHHmmss"), //
		PATTERN_ONLY_NUMER_NO_TIME("yyyyMMdd");

		private String patern;

		public String getPatern() {
			return patern;
		}

		private DatePatternEnum(String patern) {
			this.patern = patern;
		}

	}
	/**
	 * @param fecha   es un util.Date  
	 * @param patternEnum  si es Null usa yyyyMMddHHmmss
	 */
	public FechaStringOnlyNumbersVO(Date fecha, DatePatternEnum patternEnum) throws Exception {
		super();
		if(patternEnum==null){
			patternEnum = DatePatternEnum.PATTERN_ONLY_NUMER_DEFAULT;
		}
		
		SimpleDateFormat formateador = new SimpleDateFormat(patternEnum.getPatern());
		strFecha = formateador.format(fecha);
	}

	

}
