package org.krmdemo.techlabs.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Utility class to serialize / de-serialize objects to JSON
 */
@Slf4j
public class JsonUtils {

    @Getter
    @ToString
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AnyError {
        private final String exceptionClass;
        private final String message;
        private final List<String> stackTrace;
        public AnyError(@NonNull Exception ex) {
            this.exceptionClass = ex.getClass().getName();
            this.message = ex.getMessage();
            this.stackTrace = ExceptionUtils.getRootCauseStackTraceList(ex);
        }
    }

    @Getter @ToString
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JsonError extends AnyError {
        private final String originalMessage;
        private final String location;
        public JsonError(JsonProcessingException jsonEx) {
            super(jsonEx);
            this.originalMessage = jsonEx.getOriginalMessage();
            this.location = jsonEx.getLocation() == null ? "" : jsonEx.getLocation().toString();
        }
    }

    public static AnyError errorFrom(Exception ex) { return new AnyError(ex); }
    public static JsonError errorFrom(JsonProcessingException jsonEx) { return new JsonError(jsonEx); }

    private static final ObjectMapper OBJECT_MAPPER_DUMP =
        new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

    public static Map<String, Object> fromJsonRes(String resourcePath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = classLoader.getResourceAsStream(resourcePath)) {
            return OBJECT_MAPPER_DUMP.readValue(resourceStream, new TypeReference<>(){});
        } catch (IOException ioEx) {
            throw new IllegalArgumentException(
                String.format("could not load the resource by path '%s'", resourcePath), ioEx);
        }
    }

    public static String dumpAsJson(Object value) {
        try {
            return OBJECT_MAPPER_DUMP.writeValueAsString(value);
        } catch (JsonProcessingException jsonEx) {
            log.error("could not dump the value as JSON", jsonEx);
            return dumpAsJson(errorFrom(jsonEx));
        }
    }

    private static final ObjectMapper OBJECT_MAPPER_PRETTY_PRINT = objectMapperPrettyPrint();
    private static ObjectMapper objectMapperPrettyPrint() {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        objectMapper.configOverride(Map.class).setInclude(
            JsonInclude.Value.construct(JsonInclude.Include.NON_EMPTY, JsonInclude.Include.NON_EMPTY));
        return objectMapper;
    }

    public static String prettyPrintJson(String jsonToPrettify) {
        if (isBlank(jsonToPrettify)) {
            log.warn("attempt to pretty-print the blank string - return the empty string");
            return "";
        }
        try {
            Object objFromJson = OBJECT_MAPPER_DUMP.readValue(jsonToPrettify, Object.class);
            return OBJECT_MAPPER_PRETTY_PRINT.writeValueAsString(objFromJson);
        } catch (JsonProcessingException jsonEx) {
            log.error("could not pretty-print JSON-string - '" + jsonToPrettify + "''", jsonEx);
            return jsonToPrettify;
        }
    }

    private JsonUtils() {
        // prohibit the creation of utility-class instance
        throw new UnsupportedOperationException("Cannot instantiate utility-class " + getClass().getName());
    }
}
