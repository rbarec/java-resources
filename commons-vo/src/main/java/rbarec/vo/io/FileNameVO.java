package rbarec.vo.io;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Crear la ruta absoluta con nombre<br>
 * workDirectoryVO > pone la ruta<br>
 * workDirectoryVO > pone la extension<br>
 * ejemplo: /home/user/NOTARIA_HOME/tramites/777/minuta_232301.docx <br>
 * ejemplo: /home/user/NOTARIA_HOME/tramites/777/matriz_4521632.docx <br>
 * 
 * @author Ronald
 *
 */
@Log4j2
@Getter
public class FileNameVO {
	private static final String purpose = "Creacion de Nombre De Archivo VO";

	private String pathFileDirectory;
	private String pathFileAndName;
	private String relativePathFileAndName;
	private String nameFile;

	/**
	 * * Crear la ruta absoluta con nombre<br>
	 * workDirectoryVO > pone la ruta<br>
	 * workDirectoryVO > pone la extension<br>
	 * ejemplo: /home/user/NOTARIA_HOME/tramites/777/minuta_232301.docx <br>
	 * ejemplo: /home/user/NOTARIA_HOME/tramites/777/matriz_4521632.docx <br>
	 * 
	 * @author Ronald
	 * @param workDirectoryVO  pone RUTA y la EXTENSION
	 * @param onlyName_notExtension  sol opone el nombre
	 * @throws Exception
	 */
	public FileNameVO(WorkDirectoryVO workDirectoryVO, String onlyName_notExtension) throws Exception {
		log.debug(() -> FileNameVO.purpose);
		if (workDirectoryVO == null || onlyName_notExtension == null)
			throw new Exception("Error, Datos de Entrada para crear el nombre del archivo Nulos. " + FileNameVO.purpose);
		//
		// TODO PENDIENTE VO: validar los nombres de los archivos
		String extension = workDirectoryVO.getInternalDirEnum().getExtension();
		//TODO Anula la extension por el error en minuta  14Junio
		extension ="";
		
		this.pathFileAndName = workDirectoryVO.getAbsoluteWorkPath() + "/" + onlyName_notExtension + extension;
		this.pathFileDirectory = workDirectoryVO.getAbsoluteWorkPath();
		this.relativePathFileAndName= "/"+workDirectoryVO.getSubdirectoryName()+ "/" + onlyName_notExtension + extension;
		this.nameFile= onlyName_notExtension + extension;
	}

}
