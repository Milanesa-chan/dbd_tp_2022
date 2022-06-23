import java.sql.*;

public class Main {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/club";
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "estanislao";

    public static void main(String[] args) throws SQLException {
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"Puede_Desarrollarse_En\";");
        while(rs.next()){
            System.out.println(rs.getInt(1) + " " + rs.getInt(2));
        }
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
