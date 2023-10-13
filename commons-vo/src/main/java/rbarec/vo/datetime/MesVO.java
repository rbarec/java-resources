package rbarec.vo.datetime;

import java.util.Calendar;
import java.util.Date;

import lombok.Data;
import lombok.Getter;

/**
 * Value Object de numero Mes(Month).<br>
 * <b>Los VO son inmutables</b> <br>
 * Valida: Limites de mes es desde 1 hasta 12
 * 
 * @author Ronald
 *
 */
@Data
@Getter
public class MesVO {

	
	private Integer mes;

	public enum NombreMesesEnum {
		ENERO,FEBRERO,MARZO,ABRIL,MAYO,JUNIO,JULIO,AGOSTO,SEPTIEMBRE,OCTUBRE,NOVIEMBRE,DICIEMBRE;
		}
	/**
	 * Limite inferior de anios de una fecha
	 */
	public static int MES_VALOR_MINIMO = 0;
	/**
	 * Limite superior de anios de una fecha
	 */
	public static int MES_VALOR_MAXIMO = 11;

	/**
	 * Value Object de numero Mes(Month).<br>
	 * <b>Los VO son inmutables</b> <br>
	 * Valida: Limites de mes es desde 1 hasta 12 <br>
	 * 
	 * @param numberMes
	 * @throws Exception
	 * @see topnotaria.vo.MesVO
	 */
	public MesVO(int numberMes) throws Exception {
		super();
		validarLimitesValidos(numberMes);
		this.mes = numberMes;
	}

	/**
	 * REcibe una fecha no nula. y toma el anio<br>
	 * lo valida y registra.
	 * 
	 * @param fecha
	 * @throws Exception
	 */
	public MesVO(Date fecha) throws Exception {
		super();
		final int AJUSTE_MES_MAS_1 = 1;
		if (fecha == null)
			throw new Exception("MesVO Constructor, Dato de entrada es nula.");
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int mes = cal.get(Calendar.MONTH);
		this.mes = mes+AJUSTE_MES_MAS_1;
		validarLimitesValidos(mes);
	}

	/**
	 * Validar limites de meses
	 * 
	 * @param numberMEs
	 * @throws Exception
	 */
	private void validarLimitesValidos(int numberMes) throws Exception {
		if (numberMes < MES_VALOR_MINIMO || numberMes > MES_VALOR_MAXIMO) {
			throw new Exception("numero de Mes fuera de Rango!.");
		}
	}

	public NombreMesesEnum getNombreMes() {
		final int ARRAY_FIX = 1;
		return NombreMesesEnum.values()[this.mes - ARRAY_FIX];
	}

	public static void main(String[] args) throws Exception {
		// System.out.println( new MesVO(0).getNombreMes() );
		System.out.println(new MesVO(1).getNombreMes());
		System.out.println(new MesVO(2).getNombreMes());
		System.out.println(new MesVO(10).getNombreMes());
		System.out.println(new MesVO(11).getNombreMes());
		System.out.println(new MesVO(12).getNombreMes());
		// System.out.println(new MesVO(13).getNombreMes());
	}
}
