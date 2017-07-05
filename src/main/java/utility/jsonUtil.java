package utility;

/**
 * Created by KinshukBasu on 04-Jul-17.
 */
import com.google.gson.Gson;
import spark.ResponseTransformer;

public class jsonUtil {

    public static String toJson(Object obj){
        return new Gson().toJson(obj);
    }


    public static ResponseTransformer json(){
        return jsonUtil::toJson;
    }
}