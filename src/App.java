import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\User\\Documents\\JavaProject\\FirstProject\\Cmpe411\\File_IO\\Students.txt";

        Students studentManager = new Students(filePath);

        // Insert sample students
        studentManager.insert("2914", "Emmanuella", "3.9", "2025-03-21", "Female");
        studentManager.insert("1013", "Bob", "3.5", "2025-03-21", "Male");

        // Display all students
        System.out.println("All Students: ");
        studentManager.display();

        // Modify a student record
        studentManager.modify("123", "cgpa", "4.0");

        // Delete a student record
        studentManager.delete("123");
        studentManager.delete("124");

        // Display updated students
        System.out.println("\nUpdated Students:");
        studentManager.display();

        // Generate stats
        studentManager.stats();
        System.out.println("\nStats file generated.");
    }
    
}
