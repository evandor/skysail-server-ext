package de.twenty11.skysail.server.osgi.jgit.service.definition;

import org.jgroups.Channel;
import org.jgroups.Message;


public interface JGroupsService {

    public Channel getChannel ();
    
    public void send(Channel channel, String message);

    public void receive(Message msg);
    
}
