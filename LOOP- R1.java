import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManipulation {

    public static void main(String[] args) {
        String inputFilePath = "your_input_file.csv";
        String outputFilePath = "your_output_file.csv";

        try {
            // Read data from CSV
            List<String[]> data = readDataFromCSV(inputFilePath);

            // Process data and write to a new CSV file
            processDataAndWriteToCSV(data, outputFilePath);

            System.out.println("Data manipulation completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readDataFromCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build()) {
            data = reader.readAll();
        }

        return data;
    }

    private static void processDataAndWriteToCSV(List<String[]> data, String outputPath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
           
            String[] header = {"Subscription ID", "Variant ID", "Line Quantity", "Line Discounted Price"};
            writer.writeNext(header);

            // Process data and write to CSV
            for (String[] row : data) {
                String subscriptionId = row[0];
                String variantId = row[1];
                String lineQuantity = row[2];
                String lineDiscountedPrice = row[3];

                writer.writeNext(new String[]{subscriptionId, variantId, lineQuantity, lineDiscountedPrice});
            }
        }
    }
}
