package rbarec.numberstoletters.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransformaLetraResponse {
	private String entrada;
	private String salida;
	private boolean errors;
	private String msjErrors;
}
