import java.sql.*;

public class Server {
        static {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "GKdl3594c++"
            );
            Statement stmt = con.createStatement();
            StringBuffer sb = new StringBuffer();

        } catch(SQLException sqle) {
            System.out.println("DB Connection Error");
            sqle.printStackTrace();
        }
    }
}