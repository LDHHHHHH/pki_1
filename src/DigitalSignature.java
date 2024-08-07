import javax.crypto.KeyGenerator;
import java.security.*;

public class DigitalSignature {
    public static void main(String[] args) throws Exception {

        //初始化
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        //生成公钥私钥
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("私钥："+ privateKey);
        System.out.println("公钥："+ publicKey);

        String oiginalMessage = "test Message";

        //私钥数字签名
        byte[] signature = sign(oiginalMessage, privateKey);
        System.out.println("签名：" + signature);
        //公钥进行验证
        boolean verify = verify(oiginalMessage, signature, publicKey);
        System.out.println("Signature verify:" + verify);

    }

    public static byte[] sign(String message, PrivateKey privateKey) throws Exception{
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    public static boolean verify(String message, byte[] signature, PublicKey publicKey) throws Exception{
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(message.getBytes());
        return sig.verify(signature);
    }
}
