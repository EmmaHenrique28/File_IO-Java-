import java.io.*;
import java.util.*;

public class Students {
    private String filePath;

    // Constructor
    public Students(String filePath) {
        this.filePath = filePath;
    }

    // Modify method
    public void modify(String stdId, String field, String newValue) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\t");
            if (fields[0].equals(stdId)) {
                switch (field.toLowerCase()) {
                    case "name":
                        fields[1] = newValue;
                        break;
                    case "cgpa":
                        fields[2] = newValue;
                        break;
                    case "date":
                        fields[3] = newValue;
                        break;
                    case "gender":
                        fields[4] = newValue;
                        break;
                }
                line = String.join("\t", fields);
            }
            writer.write(line + System.lineSeparator());
        }

        reader.close();
        writer.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    
}
