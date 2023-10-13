package rbarec.vo.datetime;

import java.util.Calendar;
import java.util.Date;

import lombok.Data;
import lombok.Getter;

/**
 * Value Object de numero Anio(Year).<br>
 * Valida: Limites de anio es desde 1900 hasta 2200<br>
 * <b>Los VO son inmutables</b> <br>
 * 
 * RULE01- The value object should represent something simple, such as the
 * currencies in this example<br>
 * RULE02- Two objects should be equal if their actual values are equal, not
 * necessarily if they point to the same object.<br>
 * RULE03- They should be immutable when possible.<br>
 * 
 * @author Ronald
 */
@Data
@Getter
public class AnioVO {
	/**
	 * Limite inferior de anios de una fecha
	 */
	public static int ANIO_VALOR_MINIMO = 1900;
	/**
	 * Limite superior de anios de una fecha
	 */
	public static int ANIO_VALOR_MAXIMO = 2200;
	private Integer anio;

	/**
	 * Value Object de numero Anio(Year).<br>
	 * <b>Los VO son inmutables</b> <br>
	 * Valida: Limites de anio es desde 2000 hasta 2200<br>
	 * 
	 * @param numberAnio
	 * @throws Exception
	 * @see topnotaria.vo.AnioVO
	 */
	public AnioVO(int numberAnio) throws Exception {
		super();
		validarLimitesValidos(numberAnio);
		this.anio = numberAnio;
	}

	/**
	 * REcibe una fecha no nula. y toma el anio<br>
	 * lo valida y registra.
	 * 
	 * @param fecha
	 * @throws Exception
	 */
	public AnioVO(Date fecha) throws Exception {
		super();
		if (fecha == null)
			throw new Exception("AnioVO Constructor, Dato de entrada es nula.");
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int anio = cal.get(Calendar.YEAR);
		validarLimitesValidos(anio);
		this.anio = anio;
	}

	/**
	 * Validar limites de año
	 * 
	 * @param numberAnio
	 * @throws Exception
	 */
	private void validarLimitesValidos(int numberAnio) throws Exception {
		if (numberAnio < ANIO_VALOR_MINIMO || numberAnio > ANIO_VALOR_MAXIMO) {
			throw new Exception("numero de Año fuera de Rango!.");
		}
	}

}
