import java.sql.*;

public class SQLConnectTest {
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
            sb.append("create table students(num varchar(10), ");
            sb.append("age number)");
            int updateCount = stmt.executeUpdate(sb.toString());
            System.out.println("createCount : " + updateCount);

            sb.setLength(0);
            sb.append("insert into students values('91416284'");
            sb.append(", 10)");
            updateCount = stmt.executeUpdate(sb.toString());
            System.out.println("insertCount : " + updateCount);

            sb.setLength(0);
            sb.append("select * from students");
            ResultSet rs = stmt.executeQuery(sb.toString());
            while (rs.next()) {
                System.out.print("num : " + rs.getString(1) + " , ");
                System.out.println("age : " + rs.getString("age"));
            }

            sb.setLength(0);
            sb.append("update students set num = '91416285', ");
            sb.append("age=20 where num='91416284'");
            updateCount = stmt.executeUpdate(sb.toString());
            System.out.println("updateCount : " + updateCount);

            sb.setLength(0);
            sb.append("select * from students");
            rs = stmt.executeQuery(sb.toString());
            while(rs.next()) {
                System.out.print("num : " + rs.getString(1) + " , ");
                System.out.println("age : " + rs.getString("age"));
            }

            sb.setLength(0);
            sb.append("delete from students");
            updateCount = stmt.executeUpdate(sb.toString());
            System.out.println("deleteCount : " + updateCount);

            sb.setLength(0);
            sb.append("drop table students");
            updateCount = stmt.executeUpdate(sb.toString());
            System.out.println("dropCount : " + updateCount);

            rs.close();
            stmt.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println("DB Connection Error");
            sqle.printStackTrace();
        }
    }
}