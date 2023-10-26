package rbarec.numberstoletters.exceptions;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PropertiesException extends Exception {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public PropertiesException(String message) {
		super(message);
	}

	public PropertiesException(String exceptionMessage, String logMessage, Object... params) {
		super(exceptionMessage);
	}

	public PropertiesException(String exceptionMessage, String logMessage) {
		super(exceptionMessage);
	}

	public PropertiesException(Throwable throwable) {
		super(throwable);
	}

	public PropertiesException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
