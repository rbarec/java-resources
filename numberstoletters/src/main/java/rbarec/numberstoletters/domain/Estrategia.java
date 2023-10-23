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

	private static final EstrategiaEnum ESTRATEGIA_DEFAULT = EstrategiaEnum.NO_ESTRATEGY;

	/**
	 * Estrategia para una palabra
	 */
	@Setter(AccessLevel.NONE)
	private EstrategiaEnum estrategia;
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
	public void changeEstrategia(final EstrategiaEnum newEstrat) {

		this.estrategiaLog = this.estrategia == null ? newEstrat.name() : this.estrategiaLog + " - " + newEstrat.name();
		log.debug("changeEstrategia " + this.estrategiaLog);
		this.estrategia = newEstrat;
		return;
	}

	@Override
	public String toString() {
		return estrategia.name();
	}

}
