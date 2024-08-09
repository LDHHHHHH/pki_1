import java.security.KeyPair;
import java.security.cert.X509Certificate;

public class PKIExample {
    public static void main(String[] args) {
        try {
            // 1. 生成密钥对
            KeyPair keyPair = KeyPirGeneratorExample.generateKeyPair();

            // 2. 创建证书
            X509Certificate certificate = CertificateGenerator.generateCertificate(keyPair);
            System.out.println("Certificate Generated:");
            System.out.println(certificate.toString());

            // 3. 验证证书
            boolean isValid = CertificateValidator.validateCertificate(certificate);
            System.out.println("Certificate Valid: " + isValid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
