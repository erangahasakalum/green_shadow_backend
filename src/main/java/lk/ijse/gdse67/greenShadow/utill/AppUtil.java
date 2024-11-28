package lk.ijse.gdse67.greenShadow.utill;
import java.util.Base64;

public class AppUtil {
    public static String imageConvert(byte[] image){
        return Base64.getEncoder().encodeToString(image);
    }

}
