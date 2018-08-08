package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {



    public static Connection getConnection() throws SQLException {
         try {
            String url = "jdbc:mysql://127.0.0.1:3306/java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection conn = null;

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, "java", "java");

           return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
             System.out.println("class not found");
            ex.printStackTrace();
        }

        return null;
    }

}
