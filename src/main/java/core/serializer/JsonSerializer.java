package core.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonSerializer {

    private static Logger logger = LoggerFactory.getLogger(JsonSerializer.class);
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Object mapping into JSON
     * @param object - object, that will be mapped
     * @return - JSON-string
     */
    public static String getJson(Object object) {
        String json = null;
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            json = mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return json;
    }

    /**
     * Returns formatted json-string
     * Too make logs more good looking
     * If something goes wrong, returns sting without changes
     * @param value - json-string
     * @return - formatted json-string
     */
    public static String formatJson(String value) {
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            Object json = mapper.readValue(value, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            logger.debug("Unable to format json");
            logger.debug(e.getMessage());
        }
        return value;
    }

    /**
     * Mapping json string to java class
     * @param json - json string
     * @param clazz - mapping class
     * @return - mapped object
     */
    public static Object parseJson(String json, Class clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("Unable to parse json \"" + json + "\"");
        }
        return null;
    }
}
