package lk.ijse.gdse67.greenShadow.utill;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String cropImage(byte[] image){
        return Base64.getEncoder().encodeToString(image);
    }

    public static String generateUserId() {
        return "USER-"+UUID.randomUUID();
    }

}
