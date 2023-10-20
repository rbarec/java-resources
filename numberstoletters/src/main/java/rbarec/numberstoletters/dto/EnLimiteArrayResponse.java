package rbarec.numberstoletters.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnLimiteArrayResponse implements Serializable {
	private static final long serialVersionUID = 17223222180L;

	private int resultado;
	private String Estrategia;

	
	
}
