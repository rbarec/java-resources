package rbarec.vo.io;

import java.io.File;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Alista carpeta dentro del HOME.<br>
 * Si no existe la crea.
 * @author Ronald
 * TODO PENDIENTE:  if (SystemUtils.IS_OS_LINUX)
 */
@Log4j2
@Getter
public class StorageDirectoryVO {
	private final String homeWork;
	private final InternalDirEnum pricipalDirectoryInfo;
	// ------------------------
	/**
	 * Path Interno
	 */
	private final String finalPath;

	/**
	 * 
	 * @param strHomeDirectory  String HOME_DIRECTORY que viene en el app.properties
	 * @param pricipalDirectoryInfoEnum
	 * @throws Exception
	 */
	public StorageDirectoryVO(String strHomeDirectory, InternalDirEnum pricipalDirectoryInfoEnum) throws StorageDirectoyException {
		super();
		this.homeWork = strHomeDirectory;
		this.pricipalDirectoryInfo = pricipalDirectoryInfoEnum;
		//
		// Section Directory WORK - String absoplute path 
		String strAbsolutePath_directory = this.homeWork + pricipalDirectoryInfoEnum.getUri();
		File filePathRaiz = new File(strAbsolutePath_directory);
		if (!filePathRaiz.exists()) {
			final String err_ = "El Directorio Padre no existe, directorio " + strAbsolutePath_directory;
			log.debug(err_);
			throw new StorageDirectoyException(err_);
		}
		if (!filePathRaiz.isDirectory()) {
			final String err_ = "No es directorio" + strAbsolutePath_directory;
			log.debug(err_);
			throw new StorageDirectoyException(err_);
		}
		log.debug("Path Directorio exitoso");
		finalPath = strAbsolutePath_directory;
	}

	public String getFinalPath() {
		return finalPath;
	}

	public InternalDirEnum getInternalDirEnum() {
		return pricipalDirectoryInfo;
	}

}
