package rbarec.vo.controllers;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.ThreadContext;

public class LoggerVO {

	/**
	 * Etiqueta Log para id de transaccion
	 */
	public static final String CONTEXT_LABEL_TX_ID = "tx_id";
	/**
	 * Etiqueta log para id de transaccion externa
	 */
	public static final String CONTEXT_LABEL_EX_TX_ID = "exTxId";

	public static void addOneKeyDataContext(String llave, String valor) {
		CloseableThreadContext.put(llave, valor);
	}

	/**
	 * Agrega al contexto de la ejecución variables con los valores enviados por
	 * parámetros. Los parámetros son transactionId, externalTransactionId,
	 * operationId y remoteAddress. Se utiliza el método
	 * {@link CloseableThreadContext.put("llave", valor)}
	 * 
	 * @param transactionId
	 * @param externalTransactionId
	 * @param operationId
	 * @param remoteAddress
	 */
	public static void addDataContext(//
			String transactionId, //
			String externalTransactionId //
	// ,String operationId, String remoteAddress
	) {
		addOneKeyDataContext(CONTEXT_LABEL_TX_ID, transactionId);
		if (externalTransactionId != null) {
			addOneKeyDataContext(CONTEXT_LABEL_EX_TX_ID, externalTransactionId);
		}

		// addDataContext("operationId", operationId);
		// addDataContext("ipClient", remoteAddress);
		// addDataContext("ipServer",NetworkUtil.getLocalAddr());
		// addDataContext("transactionDate", new Date().getTime() + "");
	}
	
	
	public static String getDataFromContext(String key) {
		return ThreadContext.get(key);
	}

	/**
	 * Elimina todos los atributos que se agregaron al contexto de Logger!
	 */
	public static void clearLog() {
		ThreadContext.clearAll();
	}

}
