package automation.appbisscontrol;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;

/**
 * General Control sobre ServletContext
 * 
 * @author Ronald
 *
 */
@Component
public class GeneralControl {

	private static final String SEPARATOR = "___";

	public static final String DATE_WITH_SECONDS_PATTERN = "yyyy-MM-dd-HH-mm-ss";

	@Autowired
	private ServletContext servletContext;
	
	public List<String> getAllScrappingProcessActives(){
		List<String> list = Collections.list(servletContext.getAttributeNames());
		return list;
	}

	public boolean addElementServletContext(final String key, TiempoBloqueoMinutos tiempo,  String userSession) {
		servletContext.setAttribute(key, buildValue(tiempo, userSession));
		return true;
	}

	public String buildValue(TiempoBloqueoMinutos tiempo,  String userSession) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_WITH_SECONDS_PATTERN);
		return tiempo + SEPARATOR + sdf.format(new Date())+SEPARATOR +userSession;
	}

	public boolean ExistElementServletContext(final String key) {
		Object o = servletContext.getAttribute(key);
		return o == null ? false : true;
	}
	
	public String getElementServletContext(final String key) {
		Object o = servletContext.getAttribute(key);
		return o == null ? null : ((String)o);
	}

	/**
	 * TODO PENDIENTE TERMINAR 
	 * @param key
	 * @return
	 */
	public boolean ExistAndNotExpiredElementServletContext(final String key) {
		// TODO PENDIENTE TERMINAR
		Object o = servletContext.getAttribute(key);
		if (o == null)
			return false;
		try {
			String value = (String) o;
			String[] val = value.split(SEPARATOR);
			if (val.length != 2)
				return false;
			TiempoBloqueoMinutos tiempo = TiempoBloqueoMinutos.valueOf(val[0]);
			// TODO PENDIENTE TERMINAR

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void removeElementServletContext(final String key) {
		servletContext.removeAttribute(key);
	}
}
