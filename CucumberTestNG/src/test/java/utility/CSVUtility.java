package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtility {

	/**
     * Reads data from a CSV file.
     *
     * @param filePath Path to the CSV file.
     * @param delimiter The delimiter used in the CSV file.
     * @return List of string arrays representing the data in the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public static List<String[]> readCSV(String filePath, String delimiter) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                data.add(values);
            }
        }
        return data;
    }

    /**
     * Writes data to a CSV file.
     *
     * @param filePath Path to the CSV file.
     * @param data     List of string arrays representing the data to be written.
     * @param delimiter The delimiter to be used in the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeCSV(String filePath, List<String[]> data, String delimiter) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(delimiter);
                    }
                }
                bw.write(line.toString());
                bw.newLine();
            }
        }
    }
}
