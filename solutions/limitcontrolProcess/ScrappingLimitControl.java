package automation.appbisscontrol;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ScrappingLimitControl {

	private final String PREFIJO = "SCRAPPING_TRAMITE_"; 
	
	@Autowired
	private GeneralControl generalControl;

	/**
	 * 
	 * @param idTramite
	 * @throws ScrappingLimitControlException Si existe el proceso no lo deja pasar
	 */
	public void beginTramiteIfNotExist(Long idTramite, String userSession) throws ScrappingLimitControlException {
		final String KEY = KeyScrappignTramite(idTramite);
		log.debug("Key tramite "+ KEY);
		String responseContext = generalControl.getElementServletContext(KEY);
		log.debug("Buscando KEY "+ responseContext);
		if (responseContext != null) {
			log.debug("Buscando KEY, respuesta= "+ responseContext);
			throw new ScrappingLimitControlException(KEY+"/"+responseContext);
		}
		
		generalControl.addElementServletContext(KeyScrappignTramite(idTramite), TiempoBloqueoMinutos.CICO, userSession);
	}

	public boolean existTramiteInScrapping(Long idTramite) {
		return generalControl.ExistElementServletContext(KeyScrappignTramite(idTramite));
	}

	public void endTramite(Long idTramite) {
		generalControl.removeElementServletContext(KeyScrappignTramite(idTramite));
	}

	private String KeyScrappignTramite(Long idTramite) {
		return PREFIJO + idTramite;
	}
	
	public List<String> getAllTagsProcess(){
		return generalControl.getAllScrappingProcessActives() 
				.stream()
				  .filter(c -> c.contains(PREFIJO))
				  .collect(Collectors.toList());
	}
	
}
