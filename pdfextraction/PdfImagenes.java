package automation.pdfextraction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import lombok.extern.log4j.Log4j2;

/**
 * https://stackoverflow.com/questions/18098400/how-to-get-raw-text-from-pdf-file-using-java
 * 
 * @author Ronald
 *
 */
@Log4j2
public class PdfImagenes {

	public static final String READ_PDF = "C:\\Users\\Denisse\\Downloads\\DIGERCIC-CEI-BLUM_LUNA_DENISSE_LISSETTE.pdf";
	// "/0SYS/rb/NOTARIA2021/NOTARIA_HOME/TMP/CedulaPDFOficial.pdf";

	public static void main(String[] args) throws Exception {
		PdfImagenes.extractImagenToFile(READ_PDF, "C:\\TEMP\\000archivos");
	}

	/**
	 * 
	 * @param filePath
	 * @param targetDirectory
	 */
	public static void extractImagenToFile(final String filePath, String targetDirectory) {
		try {
			// Existing PDF Document
			// to be Loaded using file io
			File newFile = new File(filePath);
			PDDocument pdfDocument = PDDocument.load(newFile);

			// PDFRenderer class to be Instantiated
			// i.e. creating it's object
			PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

			// Rendering an image
			// from the PDF document
			// using BufferedImage class
			BufferedImage img = pdfRenderer.renderImage(0);
			// Writing the extracted image to a new file
			ImageIO.write(img, "JPEG", new File(targetDirectory + "\\GeeksforGeeks1.jpg"));
			BufferedImage img2 = pdfRenderer.renderImage(1);
			ImageIO.write(img2, "JPEG", new File(targetDirectory + "\\GeeksforGeeks2.jpg"));
			BufferedImage img3 = pdfRenderer.renderImage(2);
			ImageIO.write(img3, "JPEG", new File(targetDirectory + "\\GeeksforGeeks3.jpg"));

			log.debug("Image has been extracted successfully");

			// Closing the PDF document
			pdfDocument.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
