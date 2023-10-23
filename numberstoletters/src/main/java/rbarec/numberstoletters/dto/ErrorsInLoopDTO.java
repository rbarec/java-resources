package rbarec.numberstoletters.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Acumula y cuenta los errores en loop for
 * 
 * @author Ronald
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsInLoopDTO {

	private StringBuilder errors;
	private int countErrors;

	public static ErrorsInLoopDTO getInstance(){
		ErrorsInLoopDTO a = new ErrorsInLoopDTO();
		a.errors= new StringBuilder("");
		a.countErrors=0;
		return a;
	}
	public ErrorsInLoopDTO addErrors(String err){
		errors.append(err).append(" ");
		countErrors++;
		return this;
	}
	
	public boolean hasErrors() {
		return countErrors==0?false:true;
	}
}
