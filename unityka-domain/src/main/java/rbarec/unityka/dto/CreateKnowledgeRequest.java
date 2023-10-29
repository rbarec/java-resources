package rbarec.unityka.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKnowledgeRequest implements Serializable {

	private static final long serialVersionUID = 1712213180L;

	private String topicId;
	private String type;
	private String principalImage;
	private List<CreateLineKnow> lines;

	@Data
	@Builder
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateLineKnow implements Serializable {
		private static final long serialVersionUID = 1712213180L;

		private String rawOriginal;
	}
}
