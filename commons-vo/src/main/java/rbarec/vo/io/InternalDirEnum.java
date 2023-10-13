package rbarec.vo.io;

import lombok.Getter;
/**
 * Subdirectorios del HOME principal File.<br>
 * Tiene la estrategia de su contenido interno.<br>
 * y de sus archivos<br>
 * @author Ronald
 */
@Getter
public enum InternalDirEnum {

	BIOMETRICO_UPLOAD_FOLDER("/TMP/BIOMETRICO/API_INBOX", "", "ONLY_ID_PROCESS", ".txt"), //
	AUTOMATION_DOWNLOAD_FOLDER("/TMP/CHROME_DOWNLOAD", "", "ONLY_ID_PROCESS", ".txt"), //
	FORMATOS("/PROCFORMATOS", "", "ONLY_ID_PROCESS", ".txt"), //
	TRAMITE("/TRAMITE", "", "ONLY_ID_PROCESS", ".txt"), //
	REPORTES_UAF_INTERVINIENTES("/REPORTES", "", "REPORTES_UAF_INTERVINIENTES", ".xslx"), //
	REPORTES_UAF_TRAMITES("/REPORTES", "", "REPORTES_UAF_TRAMITES", ".xslx"), //
	REPORTES_ANEXO_SRI("/REPORTES", "", "REPORTES_ANEXO_SRI", ".xml"), //
	REPORTES_WAF("/REPORTES", "", "REPORTES_WAF", ".xml"), //
	TEMPLATES("/TEMPLATES", "", "ONLY_ID_PROCESS", ".txt"), //
	SCRIPT("/SCRIPT", "", "ONLY_ID_PROCESS", ".txt");//

	/**
	 * RUTA O DIRECTORIO INTERNA.
	 */
	private String uri;
	/**
	 * 
	 */
	private String patternNameFile;
	/**
	 * Patron del directorio interno, numeroProceso, idReporte etc..
	 */
	private String workDirectoryPattern;
	/**
	 * Extension final del archivo
	 */
	private String extension;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param uri
	 * @param patternNameFile
	 * @param subDirPattern
	 */
	private InternalDirEnum(String uri, String patternNameFile, String workDirPattern, String extension) {
		this.uri = uri;
		this.patternNameFile = patternNameFile;
		this.workDirectoryPattern = workDirPattern;
		this.extension = extension;
	}

}
