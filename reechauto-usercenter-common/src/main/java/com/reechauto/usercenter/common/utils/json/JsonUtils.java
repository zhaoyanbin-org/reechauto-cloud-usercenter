package com.reechauto.usercenter.common.utils.json;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
   
    static {
        // 反序列化时，忽略出现未知字段时的报错
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * JSON,使用Jackson转换Java对象.
     */
    public static <X> X renderObj(String json, Class<X> clazz) {
        try {
            return (X) objectMapper.readValue(json, clazz);
        } catch (Exception e) {
        }
        return null;
    }

    public static <X> X renderObj(Map<String, String> map, Class<X> clazz) {
        try {
            return (X) objectMapper.readValue(objectMapper.writeValueAsString(map), clazz);

        } catch (Exception e) {
        }
        return null;
    }

    public static <X> X renderObj(String jsonStr, TypeReference<X> TypeReference) {
        try {
            return objectMapper.readValue(jsonStr, TypeReference);
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
        	
        }
        return null;
    }

    public static <X> X toMap(String jsonStr, TypeReference<X> TypeReference) {
        try {
            return objectMapper.readValue(jsonStr, TypeReference);
        } catch (Exception e) {
        }
        return null;
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode readTree(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
        }
        return null;
    }

    public static <X> X renderObjByMap(Map<String, Object> map, Class<X> clazz) {
        try {
            return (X) objectMapper.readValue(objectMapper.writeValueAsString(map), clazz);

        } catch (Exception e) {
        }
        return null;
    }
}
