import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.jce.X509V3CertificateGenerator;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.jce.X509V3CertificateGenerator;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertificateGenerator {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static X509Certificate generateCertificate(KeyPair keyPair) throws Exception {
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        certGen.setSubjectDN(new X509Name("CN=Test Certificate"));
        certGen.setIssuerDN(new X509Name("CN=Test Certificate"));
        certGen.setNotBefore(new Date(System.currentTimeMillis() - 10000L * 24 * 60 * 60));
        certGen.setNotAfter(new Date(System.currentTimeMillis() + 10000L * 24 * 60 * 60));
        certGen.setPublicKey(keyPair.getPublic());
        certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");

        PrivateKey privateKey = keyPair.getPrivate();
        return certGen.generate(privateKey, "BC");
    }
}
