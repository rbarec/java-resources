package rbarec.springboot3zero.appcontext;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Persona {

	private String nombres;
	private String apellidos;
	
	
}
