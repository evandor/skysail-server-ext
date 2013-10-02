package de.twenty11.skysail.server.ext.notes;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.thucydides.core.annotations.Step;

import org.codehaus.jackson.map.ObjectMapper;

public class JacksonSteps extends CommonSteps {

    private static final long serialVersionUID = 4578237950493407488L;

    private ObjectMapper mapper = new ObjectMapper();

    @Step
    public Integer getFromJson(String element, String result) {
        Map<?, ?> folderMap;
        try {
            folderMap = mapper.readValue(result, Map.class);
            Object data = folderMap.get("data");
            if (data instanceof Map) {
                return (Integer) ((Map<?, ?>) data).get(element);
            } else if (data instanceof List) {
                List<?> dataAsList = (List<?>) data;
                return (Integer) ((Map<?, ?>) dataAsList.get(0)).get(element);
            } else {
                throw new IllegalArgumentException("cannot map data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Step
    public void assertResultIsValidJson(String result) {
        try {
            mapper.readValue(result, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

    }

}
