package utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtility {

	private String pdfText;

	public String getPdfText() {
		return pdfText;
	}

	public void readPDF(String filePath) throws IOException {
		try (PDDocument document = PDDocument.load(new File(filePath))) {
			PDFTextStripper stripper = new PDFTextStripper();
			pdfText = stripper.getText(document);
		}
	}

	public void readPDFFromURL(String pdfUrl) throws IOException {
		try (PDDocument document = PDDocument.load(new URL(pdfUrl).openStream())) {
			PDFTextStripper stripper = new PDFTextStripper();
			pdfText = stripper.getText(document);
		}
	}

	public boolean validateTextInPDF(String filePath, String searchText) throws IOException {
		readPDF(filePath);
		return pdfText.contains(searchText);
	}

	public boolean validateTextInPDFFromURL(String pdfUrl, String searchText) throws IOException {
		readPDFFromURL(pdfUrl);
		return pdfText.contains(searchText);
	}

	public int countOccurrencesInPDF(String filePath, String searchText) throws IOException {
		readPDF(filePath);
		return countOccurrences(pdfText, searchText);
	}

	public int countOccurrencesInPDFFromURL(String pdfUrl, String searchText) throws IOException {
		readPDFFromURL(pdfUrl);
		return countOccurrences(pdfText, searchText);
	}

	public String extractTextBetween(String filePath, String startText, String endText) throws IOException {
		readPDF(filePath);
		return extractTextBetween(pdfText, startText, endText);
	}

	public String extractTextBetweenFromURL(String pdfUrl, String startText, String endText) throws IOException {
		readPDFFromURL(pdfUrl);
		return extractTextBetween(pdfText, startText, endText);
	}

	private int countOccurrences(String text, String searchText) {
		int count = 0;
		int index = 0;
		while ((index = text.indexOf(searchText, index)) != -1) {
			count++;
			index += searchText.length();
		}
		return count;
	}

	private String extractTextBetweenText(String text, String startText, String endText) {
		int startIndex = text.indexOf(startText);
		int endIndex = text.indexOf(endText, startIndex + startText.length());
		if (startIndex == -1 || endIndex == -1) {
			return "";
		}
		return text.substring(startIndex + startText.length(), endIndex).trim();
	}
}
