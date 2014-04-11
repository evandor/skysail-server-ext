package de.twenty11.skysail.server.ext.osgi.monitor.agent.test;

import static org.mockito.Mockito.times;

import java.lang.instrument.Instrumentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.OsgiFrameworkTransformer;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.OsgiMonitorAgent;

/**
 * TODO find a nice way to test static call...
 * 
 */
public class OsgiMonitorAgentTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPremain() throws Exception {
        Instrumentation instrumentation = Mockito.mock(Instrumentation.class);
        Mockito.when(instrumentation.isRetransformClassesSupported()).thenReturn(false);
        OsgiMonitorAgent.premain("", instrumentation);
        Mockito.verify(instrumentation, times(0)).addTransformer(Mockito.<OsgiFrameworkTransformer> any(),
                Mockito.anyBoolean());
    }

    @Test
    public void testPremain2() throws Exception {
        Instrumentation instrumentation = Mockito.mock(Instrumentation.class);
        Mockito.when(instrumentation.isRetransformClassesSupported()).thenReturn(true);
        OsgiMonitorAgent.premain("", instrumentation);
        Mockito.verify(instrumentation, times(1)).addTransformer(Mockito.<OsgiFrameworkTransformer> any(),
                Mockito.anyBoolean());
    }
}
