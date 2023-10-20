package rbarec.numberstoletters.exceptions;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NumberLetterException extends Exception {

	public static final Integer DEFAULT_ERROR_CODE= 500;
	
	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	private String exceptionMessage;


	public NumberLetterException(String message) {
		super(message);
	}

	public NumberLetterException(String exceptionMessage, String logMessage, Object... params) {
		super(exceptionMessage);
	}

	public NumberLetterException(String exceptionMessage, String logMessage) {
		super(exceptionMessage);
	}

	public NumberLetterException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public NumberLetterException(Throwable throwable) {
		super(throwable);
	}

	public NumberLetterException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
