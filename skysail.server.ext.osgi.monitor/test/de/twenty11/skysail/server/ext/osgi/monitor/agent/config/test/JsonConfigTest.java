package de.twenty11.skysail.server.ext.osgi.monitor.agent.config.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.twenty11.skysail.server.ext.osgi.monitor.agent.MethodIdentifier;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.config.JsonConfig;
import de.twenty11.skysail.server.ext.osgi.monitor.agent.descriptors.CallbackDescriptor;


public class JsonConfigTest {

	@Test
	public void can_write_json_configuration() {
		JsonConfig jsonConfig = new JsonConfig();
		List<CallbackDescriptor> callbacks = new ArrayList<CallbackDescriptor>();
		CallbackDescriptor c1 = new CallbackDescriptor("CallbackA");
		CallbackDescriptor c2 = new CallbackDescriptor("CallbackB");
		
		c1.addMethodIdentifier(new MethodIdentifier("classname", "methodName", "signature"));
		
		callbacks.add(c1);
		callbacks.add(c2);
		jsonConfig.writeConfig(callbacks, "config/jsonConfig.backup");
	}
	
	@Test
	public void can_read_json_configuration_from_default_location() {
		JsonConfig jsonConfig = new JsonConfig();
		List<CallbackDescriptor> callbacks = jsonConfig.readConfig("/config/osgi.json");
		assertThat(callbacks.size(), is(2));
	}
}
