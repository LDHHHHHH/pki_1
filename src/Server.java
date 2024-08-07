import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;

public class Server {

    public static void main(String[] args) throws Exception {
//        try{
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器已启动，等待客户端连接...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("客户端已连接：" + clientSocket.getInetAddress());

        //生成密钥对
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        //
        String originalmessage = "verify message";
        String publickey = String.valueOf(publicKey);

        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        byte[] signature = sign(originalmessage, privateKey);

        outputStream.write(originalmessage.getBytes());
        outputStream.write(publickey.getBytes());
        outputStream.write(signature);
        outputStream.flush();
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer))!= -1){
//                String message = new String(buffer, 0, bytesRead);
//                System.out.println("客户端消息:" + message);
//
//                String response = "服务器收到的消息:" + message;
//
//                outputStream.write(response.getBytes());
//                outputStream.flush();
//            } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

//            clientSocket.close();
//            serverSocket.close();

//        } catch (IOException | NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }



//    public  Keypair() throws NoSuchAlgorithmException {
//        //生成密钥对
//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//        keyGen.initialize(2048);
//        KeyPair keyPair = keyGen.generateKeyPair();
//
//        PrivateKey privateKey = keyPair.getPrivate();
//        PublicKey publicKey = keyPair.getPublic();
//
//    }
}
