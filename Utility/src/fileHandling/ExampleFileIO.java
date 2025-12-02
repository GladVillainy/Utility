package fileHandling;
import java.util.ArrayList;
public class ExampleFileIO {
    //Made by ChatGPT to provide an example on usage
        public static void main(String[] args) {

            // 1) Lav et FileIO-objekt
            FileIO fileIO = new FileIO();

            // 2) Lav en liste med data vi vil gemme
            ArrayList<String> students = new ArrayList<>();
            students.add("Alice");
            students.add("Bob");
            students.add("Charlie");

            // 3) Gem listen i en fil
            String path = "students.txt";        // filnavn
            String header = "Student names";     // første linje i filen

            fileIO.saveData(students, path, header);
            System.out.println("Data saved to file: " + path);

            // 4) Læs data tilbage fra filen
            ArrayList<String> loadedStudents = fileIO.readData(path);

            // 5) Print hvad vi har læst
            System.out.println("\nData read from file:");
            for (String name : loadedStudents) {
                System.out.println(name);
            }
        }
    }

