package com.simplehttpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static ObjectMapper myObjectMapper = deafultObjectMapper();

    //method that creates a new object mapper
    private static ObjectMapper deafultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    //method to pass a Json String into a Json node
    public static JsonNode parse(String jsonSrc) throws IOException{
        return myObjectMapper.readTree(jsonSrc);
    }

    //method to move Json node into configuration.java
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException{
        return myObjectMapper.treeToValue(node,clazz);
    }

    //method to get configuration.java into a json node
    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException{
        return generateJson(node, false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException{
        return generateJson(node, true);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException{
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);

    }

}
