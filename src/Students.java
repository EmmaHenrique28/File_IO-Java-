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

    // Insert method
    public void insert(String stdId, String name, String cgpa, String date, String gender) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.write(stdId + "\t" + name + "\t" + cgpa + "\t" + date + "\t" + gender + System.lineSeparator());
        writer.close();
    }

    // Delete method
    public void delete(String stdId) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\t");
            if (!fields[0].equals(stdId)) {
                writer.write(line + System.lineSeparator());
        }

        reader.close();
        writer.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);

        }
    }

    // Display method
    public void display() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }

    // Stats method
    public void stats() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Map<String, Integer> genderCount = new HashMap<>();
        double totalCgpa = 0;
        int totalStudents = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\t");
            String gender = fields[4];
            double cgpa = Double.parseDouble(fields[2]);

            genderCount.put(gender, genderCount.getOrDefault(gender, 0) + 1);
            totalCgpa += cgpa;
            totalStudents++;
        }

        reader.close();

        // Write stats to stats.txt
        String statsPath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\stats.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(statsPath));

        writer.write("Male Students: " + genderCount.getOrDefault("Male", 0) + System.lineSeparator());
        writer.write("Female Students: " + genderCount.getOrDefault("Female", 0) + System.lineSeparator());
        writer.write("Average CGPA: " + (totalCgpa / totalStudents) + System.lineSeparator());
        writer.write("Total Students: " + totalStudents + System.lineSeparator());

        writer.close();
    }

}
