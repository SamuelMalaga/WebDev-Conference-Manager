package CMApplication.ConferenceManager.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographicEncoder {

    public String encodeSHA256(String stringToEncode){

        String encryptedSHA256String = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Change this to UTF-16 if needed
            md.update(stringToEncode.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();

            encryptedSHA256String = String.format("%064x", new BigInteger(1, digest));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return encryptedSHA256String;
    }

}
