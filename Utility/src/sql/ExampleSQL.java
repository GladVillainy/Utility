package sql;
import java.util.List;
public class ExampleSQL {
    //Made by ChatGPT to provide an example on usage
        public static void main(String[] args) {

            // 1. Connect to the database
            // Change the file name to your database file
            SQLUtility.connect("jdbc:sqlite:music.db");

            // 2. Read all rows from a table
            System.out.println("All rows from table 'song':");
            List<String> rows = SQLUtility.readTableData("song");

            // Print each row
            for (String row : rows) {
                System.out.println(row);
            }

            // 3. Read one column from a table
            System.out.println("\nAll titles from column 'title':");
            List<Object> titles = SQLUtility.readColumnData("song", "title");

            for (Object title : titles) {
                System.out.println(title);
            }

            // 4. Insert a new text value
            System.out.println("\nInserting a new song title...");
            boolean addedTitle = SQLUtility.addDataString("song", "title", "My New Song");

            if (addedTitle) {
                System.out.println("Title was added successfully!");
            } else {
                System.out.println("Title was NOT added.");
            }

            // 5. Insert a new integer value (for example, song length in seconds)
            System.out.println("\nInserting a new song length...");
            boolean addedLength = SQLUtility.addDataInteger("song", "length", 240);

            if (addedLength) {
                System.out.println("Length was added successfully!");
            } else {
                System.out.println("Length was NOT added.");
            }
        }
    }
