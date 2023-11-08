package automation.pdfextraction;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


import lombok.extern.log4j.Log4j2;

/**
 * https://stackoverflow.com/questions/18098400/how-to-get-raw-text-from-pdf-file-using-java
 * 
 * @author Ronald
 *
 */
@Log4j2
public class ExtractTextoPdfUtil {

	public static final String READ_PDF = "C:\\Users\\Denisse\\Downloads\\DIGERCIC-CEI-BLUM_LUNA_DENISSE_LISSETTE.pdf";
	// "/0SYS/rb/NOTARIA2021/NOTARIA_HOME/TMP/CedulaPDFOficial.pdf";

	public static void main(String args[]) {
		ExtractTextoPdfUtil.extractTextInPages(READ_PDF, 1);
	}

	/**
	 * Extraer las lineas de texto en una pagina de pdf
	 * 
	 * @param filePath
	 * @param pdfPageNumber
	 */
	public static String extractTextInPages(final String filePath, int pdfPageNumber) {
		PdfReader reader = null;
		try {
			log.debug("archivo" + filePath);
			reader = new PdfReader(filePath);
			String pageText = PdfTextExtractor.getTextFromPage(reader, pdfPageNumber);
			log.debug("Page Content:\n\n" + pageText + "\n\n");
			log.debug("Is this document tampered: " + reader.isTampered());
			log.debug("Is this document encrypted: " + reader.isEncrypted());
			reader.close();
			return pageText;
		} catch (IOException e) {
			e.printStackTrace();
			if (reader != null) {
				reader.close();
			}
			return null;
		}
	}

	/**
	 * Calcula el numeros de pagina de un PDF<br>
	 * usa un PdfReader calcula y lo cierra<br>
	 * 
	 * @param filePath
	 * @param pdfPageNumber
	 */
	public static int numberPages(final String filePath, int pdfPageNumber) {
		try {
			log.debug("archivo" + filePath);
			PdfReader reader = new PdfReader(filePath);
			log.debug("This PDF has " + reader.getNumberOfPages() + " pages.");
			int number = reader.getNumberOfPages();
			reader.close();
			return number;

		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
