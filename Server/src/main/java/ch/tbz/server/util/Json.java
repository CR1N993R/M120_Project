package ch.tbz.server.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
    public static JSONObject parseJson(String json){
        try{
            return (JSONObject) new JSONParser().parse(json);
        }catch (ParseException e){
            return null;
        }
    }
}
