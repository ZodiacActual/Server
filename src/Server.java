import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.security.*;

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
            ServerSocket listener = new ServerSocket(9999);
            System.out.println("사용자 접속 대기중....");
            Socket socket = listener.accept();
            System.out.println("사용자가 접속하였습니다. ");
            System.out.println("사용자 IP : " + socket.getInetAddress());
            OutputStream os = socket.getOutputStream();
            InputStream is =  socket.getInputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ios = new ObjectInputStream(is);
            int input = 0;
            String inputString = null;
            String outputString = null;
            boolean exit = false;
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair keypair = kpg.genKeyPair();
            PublicKey pubkey = keypair.getPublic();
            PrivateKey prikey = keypair.getPrivate();
            while (true) {

            }
        } catch(SQLException | IOException | NoSuchAlgorithmException exception) {
            System.out.println("DB Connection Error");
            exception.printStackTrace();
        }
    }
}