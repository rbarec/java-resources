package rbarec.vo.controllers;

import java.util.UUID;

import lombok.Data;
import lombok.Getter;

/**
 * HEADER Tiempo TRansaccion <br> 
 * @author Ronald
 */
@Data
@Getter
public class HeaderTransactionVO {

	private long startTransactionTime;
	private long totalTime;
	private String transactionId;
	private String ex_transactionId;
	/**
	 * Palabra nombre del HEADER, en la peticion HTTP 
	 */
	public static final String ID_KEY_HEADER = "tx_id";
	public static final String EXTERNAL_KEY_HEADER = "extx_id";
	public static final String TIME_KEY_HEADER = "tx_time";
	public static final String TIME_UNIDAD_MEDIDA = " ms";

	public HeaderTransactionVO(String externalTransactionID) {
		super();
		this.startTransactionTime = System.currentTimeMillis();
		this.transactionId = getPatterTransactionId();
		this.ex_transactionId = externalTransactionID;
	}
	
	/**
	 * Timepo total de transaccion en milisegundos
	 * @param init
	 * @return
	 */
	public String tiempoTotal() {
		totalTime = System.currentTimeMillis() - startTransactionTime;
		return "" + totalTime +TIME_UNIDAD_MEDIDA;
	}

	private String getPatterTransactionId(){
		//TODO PEND, necesita un patron ?? PREFIJO  ????? 
		return UUID.randomUUID().toString();
	}

	
}
