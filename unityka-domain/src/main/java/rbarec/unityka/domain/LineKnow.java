package rbarec.unityka.domain;

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
public class LineKnow {

	/**
	 * escrito original sin cambios
	 */
	private String rawOriginal;
}
