package clinicaodontologicadrrojas.utilities;

import lombok.extern.log4j.Log4j;

@Log4j
public class Utilities {

    public static final String encryptData(String text){
        try{
            return Crypto.encrypt(text);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return new String("FAILED ENCRYPT DATA - TRY AGAIN");
        }
    }

    public static final String decryptData(String text){
        try {
            return Crypto.decrypt(text);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new String("FAILED DECRYPT DATA - TRY AGAIN");
        }
    }
}


