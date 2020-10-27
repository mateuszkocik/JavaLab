package uj.java.pwj2020.collections;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class JsonMapperImpl implements JsonMapper {

    @Override
    public String toJson(Map<String, ?> map) {
        if(map == null || map.size() == 0) return "{}";

        String result = "{";

        for(Map.Entry<String,?> entry : map.entrySet()){
            String key = entry.getKey();
            var value = entry.getValue();
            result += "\"" + key + "\": ";
            result += getJsonFromObject(key,value);
            result += ",";
        }

        result = result.substring(0,result.length()-1);

        result += "}";

        return result;
    }

    private String getJsonFromObject(String key, Object value){
        String result = "";
        if(value instanceof List){
            result += "[" ;
            for(Object o : (List)value){
                result += getJsonFromObject(key,o) + ",";
            }
            if( ((List<?>) value).size() > 0) result = result.substring(0,result.length()-1);

            result += "]";
        }else if(value instanceof Map){
            result += m((Map)value);
        }else if(value instanceof String){
            value = ((String)value).replace("\"", "\\\"");
            result += "\"" + value + "\"";
        }else{
            result += value.toString();
        }

        return result;
    }


    

}
