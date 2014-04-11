package de.twenty11.skysail.server.ext.osgi.monitor.agent.config;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors.CallbackDescriptor;

/**
 * Zugriff auf die Konfiguration.
 * 
 */
public class JsonConfig {

    private static final Logger logger = LoggerFactory.getLogger(JsonConfig.class);

    ObjectMapper mapper = new ObjectMapper();

    private ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

    public List<CallbackDescriptor> readConfig(String filename) {
        try {
            InputStream stream = getClass().getResourceAsStream(filename);
            return mapper.readValue(stream, new TypeReference<List<CallbackDescriptor>>() {
            });
        } catch (Exception ioe) {
            logger.error("problem reading file '{}' from jar", filename);
            ioe.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void writeConfig(List<CallbackDescriptor> callbacks, String filename) {
        PrintWriter printWriter = null;
        try {
            String json = writer.writeValueAsString(callbacks);
            printWriter = new PrintWriter(filename);
            printWriter.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
