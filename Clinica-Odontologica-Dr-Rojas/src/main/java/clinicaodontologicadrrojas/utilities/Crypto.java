package clinicaodontologicadrrojas.utilities;

import lombok.extern.log4j.Log4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Log4j
public class Crypto {

    private final static String algorithm = "AES/CBC/PKCS5Padding";
    private final static SecretKey key = generateKey(128);
    public final static IvParameterSpec iv = generateIv();

    /**
     * método para generar la clave AES con el tamaño de n (128, 192 y 256) bits
     * */
    public static SecretKey generateKey(int n){
        SecretKey keyGenerated = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(n);
            keyGenerated = keyGenerator.generateKey();
            log.info("KEY GENERATED SUCCESSFULLY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyGenerated;
    }

    /**
     * método para generar la clave AES a partir de una contraseña dada con 65.536 iteraciones y una longitud de clave de 256 bits:
     * */
    public static SecretKey getKeyFromPassword(String password, String salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

    /**
     * Vector de Inicializacion, valor pseudoaleatorio y tiene el mismo tamaño que el bloque que está encriptado
     * */
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        log.info("INITIALIZER VECTOR GENERATED");
        return new IvParameterSpec(iv);
    }

    /**
     * Metodo de encriptado
     * */
    public static String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * Metodo de desencriptado
     * */
    public static String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }


}
