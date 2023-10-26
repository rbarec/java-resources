package rbarec.numberstoletters.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase response del proceso general de numeros a letras
 * 
 * @author Ronald
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumerosLetrasResponse {
	/**
	 * lista de logs
	 */
	private List<String> logs = new ArrayList<>();
	/**
	 * Contador de errores
	 */
	private int errorCounts;
	/**
	 * bloquea o no poner los logs!
	 */
	private boolean verbose = false;

	/**
	 * add nuevo log a la lista
	 * 
	 * @param log
	 */
	public void add(String log) {
		if (verbose)
			logs.add(log);
	}

	/**
	 * loogea forzado sin validar bandera verbose.
	 * 
	 * @param log
	 */
	public void forceAdd(String log) {
		logs.add(log);
	}
}
