package clinicaodontologicadrrojas.utilities.classes;

import clinicaodontologicadrrojas.utilities.Crypto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityTest {

    @Test
    public void encryptAndDecrypt(){
        try {
            String s = "juanrojas@gmail.com---micontraseña";
            String text = Crypto.encrypt(s);
            String textDecrypt = Crypto.decrypt(text);
            assertEquals(s, textDecrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}