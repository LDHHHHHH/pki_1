import java.security.cert.X509Certificate;

public class CertificateValidator {
    public static boolean validateCertificate(X509Certificate cert) throws Exception {
        try {
            cert.checkValidity();//检测证书是否有效
            cert.verify(cert.getPublicKey());//使用公钥验证证书签名是否有效
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
