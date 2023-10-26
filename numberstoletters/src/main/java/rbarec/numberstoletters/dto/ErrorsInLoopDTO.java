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

	/**
	 * Acumulador de errores generados dentro del bucle.
	 */
	private StringBuilder errors;
	/**
	 * contador de errores que se generaron dentro del bucle.
	 */
	private int countErrors;

	/**
	 * Obtener una instancia de errorInLoop<br>
	 * countErrors = 0;<br>
	 * encera el texto.<br>
	 * 
	 * @return
	 */
	public static ErrorsInLoopDTO getInstance() {
		ErrorsInLoopDTO a = new ErrorsInLoopDTO();
		a.errors = new StringBuilder("");
		a.countErrors = 0;
		return a;
	}

	/**
	 * Agregar un nuevo texto de error. aumenta contador.
	 * 
	 * @param err
	 * @return
	 */
	public ErrorsInLoopDTO addErrors(String err) {
		errors.append(err).append(" ");
		countErrors++;
		return this;
	}

	/**
	 * Si tiene errores retorna TRUE.
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return countErrors == 0 ? false : true;
	}
}
