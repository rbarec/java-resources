package rbarec.unityka.db;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbarec.unityka.domain.LineKnow;

/**
 * 
 * @author Ronald
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "unit_knowledge")
public class KnowledgeModel implements Serializable {

	private static final long serialVersionUID = -151091200367L;
	public static final String MODEL_COLLECTION = "unit_knowledge";

	@Id
	private String id;

	@Field(value = "topic_id")
	private String topicId;

	@Field(value = "type")
	private String type;

	@Field(value = "principal_image")
	private String principalImage;

	@Field(value = "version")
	private String version;

	@Field(value = "status")
	private String status;

	/**
	 * Lineas de conocimientos
	 */
	private List<LineKnowModel> lines;

	@Data
	@Builder
	@Getter
	@Setter
	@AllArgsConstructor
	public static class LineKnowModel implements Serializable {
		private static final long serialVersionUID = 17121222180L;

		private String rawOriginal;
	}

}
