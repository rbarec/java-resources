package rbarec.numberstoletters.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Acumulador de errores en loop for
 * 
 * @author Ronald
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcumErrorInLoop {

	private StringBuilder errors;
	private int countErrors;

	public static AcumErrorInLoop getInstance(){
		AcumErrorInLoop a = new AcumErrorInLoop();
		a.errors= new StringBuilder("");
		a.countErrors=0;
		return a;
	}
	public AcumErrorInLoop addErrors(String err){
		errors.append(err).append(" ");
		countErrors++;
		return this;
	}
	
	public boolean hasErrors() {
		return countErrors==0?false:true;
	}
}
