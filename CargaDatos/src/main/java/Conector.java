import java.sql.*;

public class Conector {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/club";
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "estanislao";

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
