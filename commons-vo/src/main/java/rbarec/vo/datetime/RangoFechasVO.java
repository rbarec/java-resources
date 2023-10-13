package rbarec.vo.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.Getter;

/**
 * Rango o periodo de tiempo, usa fecha_desde y fecha_hasta.<br>
 * -REGLA: Ambas fechas no pueden ser nulas <br>
 * -REGLA: <code>Fecha_desde</code> es pasado anterior a de fecha_hasta<br>
 * <br>
 * <b>Los VO son inmutables</b> <br>
 * si no cumple las reglas da EXCEPTION<br>
 * 
 * @author Ronald
 *
 */
@Getter
public class RangoFechasVO {

	private Date fechaDesde;
	private Date fechaHasta;

	/**
	 * Valida Null,
	 * Valida Periodo bien formado desde, 
	 * @param fechaDesde  Inicio del Periodo
	 * @param fechaHasta  Fin de Periodo
	 * @see topnotaria.vo.RangoFechasVO
	 */
	public RangoFechasVO(Date fechaDesde, Date fechaHasta) throws Exception {
		super();
		// REGLA: Ambas fechas no pueden ser nulas
		if (fechaDesde == null || fechaHasta == null) {
			throw new Exception("Error, Algun dato del periodo es nulo.");
		}
		// REGLA: <code>Fecha_desde</code> es pasado anterior a de fecha_hasta
		if (!fechaDesde_esAntes(fechaDesde, fechaHasta)) {
			throw new Exception("Error, Algun dato del periodo es nulo.");
		}
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;

	}

	/**
	 * TRUE: entonces fecha_desde sucede ANTES <br>
	 * if(d1.compareTo(d2) > 0)"Date2 occurs despues Date1"); <br>
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	private boolean fechaDesde_esAntes(Date fechaDesde, Date fechaHasta) {
		if (fechaDesde.compareTo(fechaHasta) < 0) {
			return true;
		}
		return false;
	}
	
	public Integer diferenciaEnDias() throws Exception {
		Calendar diferencia = new GregorianCalendar();
		diferencia.setTimeInMillis(fechaHasta.getTime() - fechaDesde.getTime());
		long milis = diferencia.getTimeInMillis();
		float diasFloat = (float) (milis / 86400000L);
		int dias = Math.round(diasFloat + 0.5F);
		return dias;
	}
	
}
