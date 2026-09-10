import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prismo_db", "root", "dul.12345");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, title, milestone_id FROM tasks")) {
            System.out.println("Tasks:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("id") + ", Title: " + rs.getString("title") + ", Milestone_ID: " + rs.getLong("milestone_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
