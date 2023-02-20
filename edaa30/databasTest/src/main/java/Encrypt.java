import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypt {

    public static String sha256(String base, String salt) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            return ConvertToHex(hash);


        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String getSalt()
    {
        //Always use a SecureRandom generator
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return ConvertToHex(salt);
    }

    private static String ConvertToHex(byte [] data){
        StringBuffer sb = new StringBuffer();

        for (Byte b : data){
            String hex = Integer.toHexString(b & 0xff);
            if (hex.length() == 1){
                sb.append("0");
            }

            sb.append(hex);
        }

        return sb.toString();
    }

}
