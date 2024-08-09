import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class CertificateValidator {
    public static boolean validateCertificate(X509Certificate cert) throws Exception {
        try {
            cert.checkValidity();
            cert.verify(cert.getPublicKey());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
