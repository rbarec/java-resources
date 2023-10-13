package rbarec.vo.io;

import java.io.File;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Crear el String Path de directorio de trabajo<br>
 * Si no existe lo crea<br>
 * @author Ronald
 *
 */
@Log4j2
@Getter
public class WorkDirectoryVO {

	private final InternalDirEnum internalDirEnum;
	/**
	 * Path de la carpeta de trabajo<br>
	 * Ejemplo C:/NOTARIA_HOME/REPORTES<br>
	 */
	private String basePath;
	/**
	 * ABSOLUTO C:/NOTARIA_HOME/REPORTES/interviniente_202329/inter_202329.xslx<br>
	 * subdirectoryName es  interviniente_202329
	 */
	private String subdirectoryName;
	private String completeWorkPath;

	/**
	 * CREA_CARPETA de trabajo, si no existe<br>
	 * Ejemplo: C:/NOTARIA_HOME/TRAMITES/152<br>
	 * Ejemplo: C:/NOTARIA_HOME/TRAMITES/153<br>
	 * Ejemplo: C:/NOTARIA_HOME/REPORTES/anexoSRI_28032023_153<br>
	 * Ejemplo: C:/NOTARIA_HOME/REPORTES/WAF_28032023_153<br>
	 * <br>
	 * 
	 * @param directoryVO  Carpeta del sistema, lista y validada.
	 * @param data1     dato para crear el nombre de carpeta
	 * @param data2		dato para crear el nombre de carpeta
	 * @param idProcess dato para crear el nombre de carpeta
	 * @throws Exception
	 */
	public WorkDirectoryVO(StorageDirectoryVO directoryVO, String data1, String data2, String idProcess)
			throws Exception {
		super();
		if (directoryVO == null)
			throw new Exception("Problemas de Directorios nulos o no instanciados, StorageDirectoryVO=null");
		internalDirEnum = directoryVO.getInternalDirEnum();
		basePath =directoryVO.getFinalPath();
		//
		subdirectoryName = null;
		//
//		if (StorageDEF.workDirectoryPattern.ONLY_ID_PROCESS.name().equals(internalDirEnum.getWorkDirectoryPattern())) {
//			log.debug("Creando un directorio de trabajo. Estrategia ID_PROCESS");
//			if (idProcess == null)
//				throw new Exception("Error, Intentando crear directorio de trabajo, idProcess Nulo.");
//			subdirectoryName = "" + idProcess;
//		}
//		final String rasInterviniente =  StorageDEF.workDirectoryPattern.REPORTES_UAF_INTERVINIENTES.name();
//		if (rasInterviniente.equals(internalDirEnum.getWorkDirectoryPattern())) {
//			log.debug("Creando un directorio de trabajo. Estrategia REPORTES_ANEXO_SRI INTERVINIENTES");
//			data1 = data1==null?"data1":data1;
//			data2 = data2==null?"data2":data2;
//			subdirectoryName = "" + data1+"_"+data2;
//
//		}
//		final String rasTramite =  StorageDEF.workDirectoryPattern.REPORTES_UAF_TRAMITES.name();
//		if (rasTramite.equals(internalDirEnum.getWorkDirectoryPattern())) {
//			log.debug("Creando un directorio de trabajo. Estrategia REPORTES_ANEXO_SRI TRAMITE");
//			data1 = data1==null?"data1":data1;
//			data2 = data2==null?"data2":data2;
//			subdirectoryName = "" + data1+"_"+data2;
//		}
//		final String rasAnexo=  StorageDEF.workDirectoryPattern.REPORTES_ANEXO_SRI.name();
//		if (rasAnexo.equals(internalDirEnum.getWorkDirectoryPattern())) {
//			log.debug("Creando un directorio de trabajo. Estrategia REPORTES_ANEXO_SRI");
//			data1 = data1==null?"data1":data1;
//			data2 = data2==null?"data2":data2;
//			subdirectoryName = "" + data1+"_"+data2;
//
//		}
//		if (StorageDEF.workDirectoryPattern.FORMATOS.name().equals(internalDirEnum.getWorkDirectoryPattern())) {
//			log.debug("Creando un directorio de trabajo. Estrategia FORMATOS");
//			if (idProcess == null)
//				throw new Exception("Error, Intentando crear directorio de trabajo, idProcess Nulo.");
//			subdirectoryName = data1 +"_" + idProcess;
//
//		}
		if (subdirectoryName == null)
			throw new Exception("No se puede crear el Directorio de trabajo, workDirectoryPattern no mapea. value:"
					+ internalDirEnum.getWorkDirectoryPattern());

		completeWorkPath = directoryVO.getFinalPath() + "/" + subdirectoryName;
		log.debug("Path completo de trabajo: "+completeWorkPath);
		log.debug("creando el directorio si no exite ");
		File filePath = new File(completeWorkPath);
		if (!filePath.exists()) {
			log.debug("creando el directorio");
			filePath.mkdir();
			log.debug("creando el directorio");
			if (filePath.exists())
				log.debug("El directorio fue creado exitosamente.");
		}
		
	}

	public String getAbsoluteWorkPath() {
		return completeWorkPath;
	}
	
}
