package org.oopdev.xbalancer.common.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kamilbukum on 31/03/2017.
 */
public class PropertyConverter {
    /**
     * Provides to convert from yaml2javaPojo and javaPojo2yaml
     */
    public static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    static {
        // YAML_MAPPER.enable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
    }

    /**
     * Provides to convert from yaml2javaPojo and javaPojo2yaml
     */
    public static final JavaPropsMapper PROPS_MAPPER = new JavaPropsMapper();

    static {
        PROPS_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    /**
     * Provides to convert from json2javaPojo and javaPojo2json
     */
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    /**
     * gets converter by property type {@link PropertySource.Type}
     *
     * @param type
     * @return
     */
    public static ObjectMapper getMapper(PropertySource.Type type) {
        switch (type) {
            case json:
                return JSON_MAPPER;
            case properties:
                return PROPS_MAPPER;
            case yml:
            case yaml:
                return YAML_MAPPER;
        }
        return null;
    }

    /**
     * Provides to convert property by the given propertySource and targetClass type
     *
     * @param propertySource
     * @param targetClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T convert(PropertySource<InputStream> propertySource, Class<T> targetClass) throws IOException {
        ObjectMapper mapper = getMapper(propertySource.getType());
        return mapper.readValue(propertySource.getInput(), targetClass);
    }

    /**
     * Provides to convert fromObject to an instance which is implemented from the given targetClass
     *
     * @param fromObject
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T convert(Object fromObject, Class<T> targetClass) {
        return JSON_MAPPER.convertValue(fromObject, targetClass);
    }

    /**
     * Provides to convert fromObject to an instance which is implemented from the given {@link TypeReference}targetClass
     *
     * @param fromObject
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T convert(Object fromObject, TypeReference<T> targetClass) {
        return JSON_MAPPER.convertValue(fromObject, targetClass);
    }
}
