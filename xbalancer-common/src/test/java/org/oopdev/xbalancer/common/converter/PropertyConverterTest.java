package org.oopdev.xbalancer.common.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by kamilbukum on 31/03/2017.
 */
public class PropertyConverterTest {

    /**
     * # ConfigurationTestModel
     * ---
     * name: Kamil
     * surname: Bukum
     * age: 31
     * languages:
     * - English
     * - Turkish
     * - Japanese
     * - Kurdish
     */
    static ConfigurationTestModel expectedModel = new ConfigurationTestModel();

    static {
        expectedModel.setAge(31);
        expectedModel.setName("Kamil");
        expectedModel.setSurname("Bukum");
        List<String> list = new ArrayList<>();
        /**
         *
         */
        list.add("English");
        list.add("Turkish");
        list.add("Japanese");
        list.add("Kurdish");
        expectedModel.setLanguages(list);
    }


    @Test
    public void getMapper() throws Exception {
        /**
         * Check Json Mapper
         */
        ObjectMapper jsonMapper = PropertyConverter.getMapper(PropertySource.Type.json);
        assertEquals(jsonMapper, PropertyConverter.JSON_MAPPER);

        /**
         * Check Property Mapper
         */
        ObjectMapper propertyMapper = PropertyConverter.getMapper(PropertySource.Type.properties);
        assertEquals(propertyMapper, PropertyConverter.PROPS_MAPPER);

        /**
         * Check Property Mapper
         */
        ObjectMapper yamlMapper = PropertyConverter.getMapper(PropertySource.Type.yaml);
        assertEquals(yamlMapper, PropertyConverter.YAML_MAPPER);
        ObjectMapper ymlMapper = PropertyConverter.getMapper(PropertySource.Type.yml);
        assertEquals(ymlMapper, PropertyConverter.YAML_MAPPER);
    }

    @Test
    public void convertFromPropertySource() throws Exception {


        PropertySource<InputStream> source = new PropertySource<>(
                PropertySource.Type.yml,
                PropertyConverterTest.class.getResourceAsStream("/application.yml")
        );
        ConfigurationTestModel model = PropertyConverter.convert(source, ConfigurationTestModel.class);

        assertEquals(expectedModel, model);

        source = new PropertySource<>(
                PropertySource.Type.yaml,
                PropertyConverterTest.class.getResourceAsStream("/application.yaml")
        );
        model = PropertyConverter.convert(source, ConfigurationTestModel.class);

        assertEquals(expectedModel, model);

        source = new PropertySource<>(
                PropertySource.Type.json,
                PropertyConverterTest.class.getResourceAsStream("/application.json")
        );
        model = PropertyConverter.convert(source, ConfigurationTestModel.class);

        assertEquals(expectedModel, model);

        source = new PropertySource<>(
                PropertySource.Type.properties,
                PropertyConverterTest.class.getResourceAsStream("/application.properties")
        );
        model = PropertyConverter.convert(source, ConfigurationTestModel.class);

        assertEquals(expectedModel, model);
    }

    @Test
    public void convertFromObjectToClass() throws Exception {
        Map<String, Object> objectMap = new LinkedHashMap<>();
        objectMap.put("name", expectedModel.getName());
        objectMap.put("surname", expectedModel.getSurname());
        objectMap.put("age", expectedModel.getAge());
        objectMap.put("languages", expectedModel.getLanguages());

        ConfigurationTestModel model = PropertyConverter.convert(objectMap, ConfigurationTestModel.class);
        assertEquals(expectedModel, model);
    }

    @Test
    public void convertFromObjectToTypeReferenceClass() throws Exception {
        Map<String, Object> objectMap = new LinkedHashMap<>();
        objectMap.put("name", expectedModel.getName());
        objectMap.put("surname", expectedModel.getSurname());
        objectMap.put("age", expectedModel.getAge());
        objectMap.put("languages", expectedModel.getLanguages());

        ConfigurationTestModel model = PropertyConverter.convert(objectMap, new TypeReference<ConfigurationTestModel>() {
        });
        assertEquals(expectedModel, model);
    }

}