import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        try{
        Socket Socket = new Socket("localhost", 8080);
        System.out.println("服务器已启动，等待客户端连接...");

        InputStream inputStream = Socket.getInputStream();
        OutputStream outputStream = Socket.getOutputStream();

        //接收约定好的消息
        byte[] originalmessage_buffer = new byte[1024];
        int bytesRead_original = inputStream.read(originalmessage_buffer);
        String originalmessage = new String(originalmessage_buffer, 0, bytesRead_original);

        byte[] publickey_buffer = new byte[1024];
        int bytesRead_publickey = inputStream.read(originalmessage_buffer);
        String publickey = new String(originalmessage_buffer, 0, bytesRead_publickey);

        byte[] publickey_buffer = new byte[1024];
        int bytesRead_publickey = inputStream.read(originalmessage_buffer);
        String publickey = new String(originalmessage_buffer, 0, bytesRead_publickey);



        boolean verify = verify();

//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("请输入消息");
//            String message = scanner.nextLine();
//
//            outputStream.write(message.getBytes());
//            outputStream.flush();
//
//            byte[] buffer = new byte[1024];
//            int bytesRead = inputStream.read(buffer);
//            String response = new String(buffer, 0, bytesRead);
//            System.out.println("服务器回复:" + response);
//
//        }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static boolean verify(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(message.getBytes());
        return sig.verify(signature);
    }
}
