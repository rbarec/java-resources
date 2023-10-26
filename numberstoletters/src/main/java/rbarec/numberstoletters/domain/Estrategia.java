package rbarec.numberstoletters.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * Estrategia de conversion de Palabra
 * 
 * @author Ronald
 *
 */
@Log4j2
@Getter
@Setter
public class Estrategia {

	/**
	 * En caso de error o ambiguedad aplicar la estrategia DEFAULT.
	 */
	private static final TipoEstrategiaEnum ESTRATEGIA_DEFAULT = TipoEstrategiaEnum.NO_ESTRATEGY;

	/**
	 * Estrategia para una palabra
	 */
	@Setter(AccessLevel.NONE)
	private TipoEstrategiaEnum tipoEnum;
	/**
	 * Historico acumulado de estrategia de una palabra
	 */
	@Setter(AccessLevel.NONE)
	private String estrategiaLog;

//	private boolean estrategiaNumericaFlag;

	/**
	 * crea object with ESTRATEGIA_DEFAULT
	 */
	public Estrategia() {
		super();
		changeEstrategia(ESTRATEGIA_DEFAULT);
	}

	/**
	 * Unico Metodo de ajuste de estrategia
	 * 
	 * @param newEstrat
	 * @return
	 */
	public void changeEstrategia(final TipoEstrategiaEnum newEstrat) {
		this.estrategiaLog = this.tipoEnum == null ? newEstrat.name() : this.estrategiaLog + " - " + newEstrat.name();
		log.debug("changeEstrategia " + this.estrategiaLog);
		this.tipoEnum = newEstrat;
		return;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNoStrategy() {
		return this.getTipoEnum() == TipoEstrategiaEnum.NO_ESTRATEGY;
	}

	@Override
	public String toString() {
		return tipoEnum.name();
	}

}
