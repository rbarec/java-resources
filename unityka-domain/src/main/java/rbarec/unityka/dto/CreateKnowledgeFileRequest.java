package rbarec.unityka.dto;

import java.io.Serializable;

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
public class CreateKnowledgeFileRequest implements Serializable {

	private static final long serialVersionUID = 1712213180L;

	
	private String fileUrl;
}
