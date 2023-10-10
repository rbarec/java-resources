package automation.appbisscontrol;

import java.io.Serializable;

public class ScrappingLimitControlException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;

	public ScrappingLimitControlException(String message) {
		this.exceptionMessage = message;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

}