package rbarec.numberstoletters.exceptions;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SpecificTransformException extends Exception {

	public static final Integer DEFAULT_ERROR_CODE= 500;
	
	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	private String exceptionMessage;


	public SpecificTransformException(String message) {
		super(message);
	}

	public SpecificTransformException(String exceptionMessage, String logMessage, Object... params) {
		super(exceptionMessage);
	}

	public SpecificTransformException(String exceptionMessage, String logMessage) {
		super(exceptionMessage);
	}

	public SpecificTransformException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public SpecificTransformException(Throwable throwable) {
		super(throwable);
	}

	public SpecificTransformException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
