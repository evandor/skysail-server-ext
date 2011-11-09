package de.twenty11.skysail.server.osgi.jgit.service.definition;

import org.jgroups.Channel;

public class ServicesProvider {

    private JGroupsService jGroupsService;

    protected void bind(JGroupsService service) {
        this.jGroupsService = service;
        Channel channel = service.getChannel();
        service.send(channel, "testimplementation sending jgroups message");
    }
    
    protected void unbind(JGroupsService service) {
        this.jGroupsService = null;
    }
}
