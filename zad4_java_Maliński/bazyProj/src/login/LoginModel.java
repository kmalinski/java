package login;


import dbConnection.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginModel {

        Connection conn;

        public LoginModel() {
            try {
                this.conn = dbConnection.getConnection();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            if(this.conn == null)
                System.exit(1);

        }

        public boolean isConnected() {
            return this.conn != null;
        }

        public boolean checkLoginData(String user, String pass) throws Exception {
            PreparedStatement pr = null;
            ResultSet result = null;

            String sql = "SELECT * FROM konto where username=? and password = ?;";
            try {
                pr = this.conn.prepareStatement(sql);
                pr.setString(1, user);
                pr.setString(2, pass);

                result = pr.executeQuery();
                if(result.next()) {
                    return true;
                }
                else    return false;
            }
            catch (SQLException e) {
                return false;
            }
            finally {
                pr.close();
                result.close();
            }
        }
}
