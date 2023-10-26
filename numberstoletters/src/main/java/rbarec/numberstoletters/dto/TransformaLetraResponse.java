package rbarec.numberstoletters.dto;

import lombok.Builder;
import lombok.Data;
/**
 * Clase response
 * @author Ronald
 *
 */
@Builder
@Data
public class TransformaLetraResponse {
	/**
	 * texto de entrada
	 */
	private String entrada;
	/**
	 * texto salida o respuesta
	 */
	private String salida;
	/**
	 * bandera que dice si hay errores.
	 */
	private boolean errors;
	/**
	 * mensajes acumulado de errores.
	 */
	private String msjErrors;
}
