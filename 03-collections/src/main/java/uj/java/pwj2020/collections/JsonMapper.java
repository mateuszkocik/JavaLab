package uj.java.pwj2020.collections;

import java.util.Map;

public interface JsonMapper {

    String toJson(Map<String, ?> map);

    static JsonMapper defaultInstance() {
        return new JsonMapperImpl();
    }

}
