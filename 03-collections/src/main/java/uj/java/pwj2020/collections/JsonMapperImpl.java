package uj.java.pwj2020.collections;

import java.util.List;
import java.util.Map;

public class JsonMapperImpl implements JsonMapper {

    @Override
    public String toJson(Map<String, ?> map) {
        if(map == null || map.size() == 0) return "{}";

        String jsonString = "{";
        jsonString = getJsonFromMap(map, jsonString);
        jsonString = jsonString.substring(0,jsonString.length()-1);
        jsonString += "}";

        return jsonString;
    }

    private String getJsonFromObject(String key, Object value){
        String jsonObject = "";
        if(value instanceof List){
            jsonObject += "[" + getJsonFromList(key,value) + "]";
        }else if(value instanceof Map){
            jsonObject += toJson((Map)value);
        }else if(value instanceof String){
            jsonObject += "\"" + ((String)value).replace("\"", "\\\"") + "\"";
        }else{
            jsonObject += value.toString();
        }

        return jsonObject;
    }

    private String getJsonFromMap(Map<String, ?> map, String jsonString) {
        for(Map.Entry<String,?> entry : map.entrySet()){
            String key = entry.getKey();
            var value = entry.getValue();
            jsonString += "\"" + key + "\": ";
            jsonString += getJsonFromObject(key,value);
            jsonString += ",";
        }
        return jsonString;
    }

    private String getJsonFromList(String key, Object value){
        var list = (List)value;
        String jsonFromList = "";

        for(Object o : list){
            jsonFromList += getJsonFromObject(key,o) + ",";
        }
        if(list.size() > 0) jsonFromList = jsonFromList.substring(0,jsonFromList.length()-1);

        return jsonFromList;
    }

}
