package de.twenty11.skysail.server.ext.atmosphere.tail;

import org.atmosphere.cache.UUIDBroadcasterCache;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;
import org.atmosphere.plugin.redis.RedisBroadcaster;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

@ManagedService(path = "/chat",
        broadcaster = RedisBroadcaster.class)
public class ChatExampleHandler extends OnMessage<String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(AtmosphereResponse response, String message) throws IOException {
        response.write(mapper.writeValueAsString(mapper.readValue(message, Data.class)));
    }
}
