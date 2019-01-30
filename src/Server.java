import org.bouncycastle.jcajce.provider.symmetric.AES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Formatter;
import java.util.Scanner;
import java.sql.*;
public class Server {
        static {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }
    public static byte[] RSAEncrypt(PublicKey pubkey,SecretKey secretkey) throws GeneralSecurityException{
        byte[] secretkeybyte = secretkey.getEncoded();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubkey);
        byte[] encryptdata = cipher.doFinal(secretkeybyte);
        return encryptdata;
    }
    //공개키 복호화(RSA)
    public static byte[] RSADecrypt(PrivateKey prikey, byte[] encryptedSecretkeybyte) throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);
        byte[] secretkeybyte = cipher.doFinal(encryptedSecretkeybyte);
        return secretkeybyte;
    }
    //대칭키 암호화(AES)
    public static byte[] AESencrypt(SecretKey secretKey, byte[] plainData) throws GeneralSecurityException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptData = cipher.doFinal(plainData);
        return encryptData;
    }

    // 대칭키 복호화(AES)
    public static byte[] AESdecrypt(SecretKey secretKey, byte[] encryptData) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainData = cipher.doFinal(encryptData);
        return plainData;
    }

    public static void main(String[] args) {

        try {
            //JDBC 설정
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

            //네트워크 객체 생성
            OutputStream os = socket.getOutputStream();
            InputStream is =  socket.getInputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);
            int input = 0;
            String inputString = null;
            String outputString = null;

            boolean exit = false;

            //암호화키 생성시작
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair keypair = kpg.genKeyPair();
            PublicKey pubkey = keypair.getPublic();
            PrivateKey prikey = keypair.getPrivate();

            //키교환 시작
            byte[] key = pubkey.getEncoded();
            oos.writeObject(key);
            oos.flush();

            byte[] clientkey = (byte[])ois.readObject();
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey clientKey = kf.generatePublic(new X509EncodedKeySpec(clientkey));//키 복구
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");//알고리즘은 AES
            keyGen.init(256);//256비트 AES키 생성
            SecretKey secretKey = keyGen.generateKey();
            oos.writeObject(RSAEncrypt(clientKey, secretKey));
            oos.flush();
            while (true) {

            }
        } catch(SQLException | IOException | ClassNotFoundException |GeneralSecurityException exception) {
            System.out.println("DB Connection Error");
            exception.printStackTrace();
        }
    }
}