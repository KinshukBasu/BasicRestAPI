/**
 * Created by KinshukBasu on 04-Jul-17.
 */

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtil {

    public static String toJson(Object obj){
        return new Gson().toJson(obj);
    }


    public static ResponseTransformer json(){
        return JsonUtil::toJson;
    }
}
