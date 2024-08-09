package utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtility {

	public static String pdfText;

	public boolean validateTextInPDF(String filePath, String searchText) throws IOException {
		pdfText = readPDF(filePath);
		return pdfText.contains(searchText);
	}

	public String readPDF(String filePath) throws IOException {
		PDDocument document = null;
		try {
			File file = new File(filePath);
			document = PDDocument.load(file);
			PDFTextStripper stripper = new PDFTextStripper();
			return stripper.getText(document);
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	public String readPDFFromURL(String pdfUrl) throws IOException {
		PDDocument document = null;
		try {
			URL url = new URL(pdfUrl);
			document = PDDocument.load(url.openStream());
			PDFTextStripper stripper = new PDFTextStripper();
			return stripper.getText(document);
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	public boolean validateTextInPDFFromURL(String pdfUrl, String searchText) throws IOException {
		pdfText = readPDFFromURL(pdfUrl);
		return pdfText.contains(searchText);
	}

}
