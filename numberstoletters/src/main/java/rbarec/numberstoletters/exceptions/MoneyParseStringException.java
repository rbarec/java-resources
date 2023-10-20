package rbarec.numberstoletters.exceptions;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MoneyParseStringException extends Exception {

	public static final Integer DEFAULT_ERROR_CODE= 500;
	
	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	private String exceptionMessage;


	public MoneyParseStringException(String message) {
		super(message);
	}

	public MoneyParseStringException(String exceptionMessage, String logMessage, Object... params) {
		super(exceptionMessage);
	}

	public MoneyParseStringException(String exceptionMessage, String logMessage) {
		super(exceptionMessage);
	}

	public MoneyParseStringException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public MoneyParseStringException(Throwable throwable) {
		super(throwable);
	}

	public MoneyParseStringException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}
