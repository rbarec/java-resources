package rbarec.unityka.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Knowledge {

	/**
	 * Id database
	 */
	private String storeId;
	/**
	 * codigo que identifica Topic<br>
	 * El Topic es un conjunto de datos de diferentes niveles o estratos.
	 */
	private String topicId;
	/**
	 * Tipo de conocimientos
	 */
	private KnowledgeTypeEnum type;
	/**
	 * Imagen Principal
	 */
	private String principalImage;
	/**
	 * 
	 */
	private String version;
	/**
	 * Version de este conocimiento
	 */
	private StatusEnum status;
	
	/**
	 * Lineas de conocimientos
	 */
	private List<LineKnow> lines;
}
