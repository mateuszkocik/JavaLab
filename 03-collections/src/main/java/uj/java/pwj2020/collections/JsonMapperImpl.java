package uj.java.pwj2020.collections;

import java.util.List;
import java.util.Map;

public class JsonMapperImpl implements JsonMapper{

    @Override
    public String toJson(Map<String, ?> map){
        if(map == null || map.size() == 0) return "{}";

        StringBuilder jsonString = new StringBuilder("{");
        jsonString.append(getJsonFromMap(map));
        jsonString.deleteCharAt(jsonString.length() - 1);
        jsonString.append("}");

        return jsonString.toString();
    }

    private String getJsonFromObject(String key, Object value){
        StringBuilder jsonObject = new StringBuilder("");
        if(value instanceof List){
            jsonObject.append("[");
            jsonObject.append(getJsonFromList(key, value));
            jsonObject.append("]");
        }else if(value instanceof Map){
            jsonObject.append(toJson((Map<String, ?>) value));
        }else if(value instanceof String){
            jsonObject.append("\"");
            jsonObject.append(((String) value).replace("\"", "\\\""));
            jsonObject.append("\"");
        }else{
            jsonObject.append(value);
        }

        return jsonObject.toString();
    }

    private String getJsonFromMap(Map<String, ?> map){
        StringBuilder jsonString = new StringBuilder();
        for(Map.Entry<String, ?> entry : map.entrySet()){
            String key = entry.getKey();
            var value = entry.getValue();
            jsonString.append('\"');
            jsonString.append(key);
            jsonString.append("\": ");
            jsonString.append(getJsonFromObject(key, value));
            jsonString.append(',');
        }

        return jsonString.toString();
    }

    private String getJsonFromList(String key, Object value){
        var list = (List) value;
        StringBuilder jsonFromList = new StringBuilder();

        for(Object o : list){
            jsonFromList.append(getJsonFromObject(key, o));
            jsonFromList.append(",");
        }
        if(list.size() > 0) jsonFromList.deleteCharAt(jsonFromList.length() - 1);

        return jsonFromList.toString();
    }

}
