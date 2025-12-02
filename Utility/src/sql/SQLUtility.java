package sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//JavaDoc is made by both ChatGPT and Lucas

/**
 * A small helper class for working with a database.
 * All methods are static, so you do not need to create an object.
 * Call the methods like: DatabaseUtility.connect("jdbc:sqlite:music.db");
 */
public class SQLUtility {

    // This stores the connection to the database.
    private static Connection connection;

    /**
     * Connects to a database using a URL.
     *
     * @param url the full database URL (for example: "jdbc:sqlite:music.db")
     */
    public static void connect(String url) {
        try {
            // Open a connection to the database
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Could not connect to database: " + url);
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Reads all rows from a table and returns them as text lines.
     * Each line is one row with all columns joined together.
     *
     * @param tableName the name of the table (for example: "song")
     * @return a list of strings, where each string is one row from the table
     */
    public static List<String> readTableData(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        List<String> rows = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Find how many columns are in the table
            int columnCount = rs.getMetaData().getColumnCount();

            // Loop through each row
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                // Add each column value to the row string
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i)).append(" | ");
                }
                rows.add(row.toString());
            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Could not read table. Check if the name is correct.");
            throw new RuntimeException(e);
        }

        return rows;
    }

    /**
     * Reads one column from a table and returns all values in that column.
     *
     * @param tableName  the name of the table
     * @param columnName the name of the column to read
     * @return a list of values from the column (each item is one row)
     */
    public static List<Object> readColumnData(String tableName, String columnName) {
        String sql = "SELECT " + columnName + " FROM " + tableName;
        List<Object> values = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through each row and get the value from the column
            while (rs.next()) {
                values.add(rs.getObject(columnName));
            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Could not read column. Check table and column names.");
            throw new RuntimeException(e);
        }

        return values;
    }

    /**
     * Adds a text (String) value into one column in a table.
     *
     * @param tableName  the name of the table
     * @param columnName the name of the column
     * @param value      the text to insert
     * @return true if at least one row was inserted, false otherwise
     */
    public static boolean addDataString(String tableName, String columnName, String value) {
        String sql = "INSERT INTO " + tableName + " (" + columnName + ") VALUES ('" + value + "')";

        try {
            Statement stmt = connection.createStatement();
            int rowsChanged = stmt.executeUpdate(sql);
            stmt.close();

            // rowsChanged is the number of rows that were affected
            return rowsChanged > 0;
        } catch (SQLException e) {
            System.out.println("Could not add text value.\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Adds an integer (whole number) value into one column in a table.
     *
     * @param tableName  the name of the table
     * @param columnName the name of the column
     * @param value      the number to insert
     * @return true if at least one row was inserted, false otherwise
     */
    public static boolean addDataInteger(String tableName, String columnName, int value) {
        String sql = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (" + value + ")";

        try {
            Statement stmt = connection.createStatement();
            int rowsChanged = stmt.executeUpdate(sql);
            stmt.close();

            return rowsChanged > 0;
        } catch (SQLException e) {
            System.out.println("Could not add integer value.\n" + e.getMessage());
            return false;
        }
    }
}
