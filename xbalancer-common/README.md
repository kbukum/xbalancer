## xbalancer-common

Common is hold most of used operations in all projects.


#### Common Classes

##### org.oopdev.xbalancer.common.converter

- PropertyConverter.convert(PropertySource) : Provides to convert property by the given type and inputStream

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.oopdev.xbalancer.common.converter.PropertyConverter;

public class Test {
    
    public convertTest(){
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
}
```

##### org.oopdev.xbalancer.common.exception

- ErrorCode : Holds Http Status Code and message as `enum`
- Status : Holds custom Status code and Message  
- XbCommonException: Used to throws error with error code.


##### org.oopdev.xbalancer.common.lang

- Response : Holds Generic Data and code 

#### org.oopdev.xbalancer.common.util

- Paths: Provides some utility operations for paths.
- Strings: Provides some utility operations for Strings.







