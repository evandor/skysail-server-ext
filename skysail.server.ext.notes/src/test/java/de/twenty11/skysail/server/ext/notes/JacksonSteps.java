package de.twenty11.skysail.server.ext.notes;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

import org.codehaus.jackson.map.ObjectMapper;

public class JacksonSteps extends ScenarioSteps {

    private static final long serialVersionUID = 4578237950493407488L;

    private ObjectMapper mapper = new ObjectMapper();

    public JacksonSteps(Pages pages) {
        super(pages);
    }

    @Step
    public Integer getFromJson(String string, String result) {
        Map<?, ?> folderMap;
        try {
            folderMap = mapper.readValue(result, Map.class);
            return (Integer) ((Map<?, ?>) folderMap.get("data")).get("pid");
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
