package rbarec.vo.uuid;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
/**
 * Prefix Uuid random sequence
 * @author Ronald
 *
 */
@Log4j2
public class UuidSequenceVO {

	private String strPrefijo;
	private String strUuid;

	@Getter
	@AllArgsConstructor
	public static enum TipoEnum {
		default0("generacion por defecto, uuid tipo de generacion.");

		private String descripcion;
	}

	public UuidSequenceVO(String prefijo) {
		TipoEnum tipoGeneracion = TipoEnum.default0;
		log.debug(() -> "UuidSequence tipoGeneracion" + tipoGeneracion);
		strPrefijo = prefijo;
		strUuid = UUID.randomUUID().toString();
	}

	public UuidSequenceVO(TipoEnum UuidSequence_tipoGeneracion, String prefijo) {
		log.debug(() -> "UuidSequence tipoGeneracion" + UuidSequence_tipoGeneracion);
		strPrefijo = prefijo;
		strUuid = UUID.randomUUID().toString();
	}
	
	public String getUuid(){
		return strPrefijo+strUuid;
	}
	
}
