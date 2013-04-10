package de.twenty11.skysail.server.ext.activiti.test;

import org.junit.BeforeClass;

public class AbstractTest {

    @BeforeClass
    public static void routeLoggingToSlf4j() {
        // LogUtil.readJavaUtilLoggingConfigFromClasspath();
        // Logger rootLogger = LogManager.getLogManager().getLogger("");
        // Handler[] handlers = rootLogger.getHandlers();
        // for (int i = 0; i < handlers.length; i++) {
        // rootLogger.removeHandler(handlers[i]);
        // }
        // SLF4JBridgeHandler.install();
    }
}
