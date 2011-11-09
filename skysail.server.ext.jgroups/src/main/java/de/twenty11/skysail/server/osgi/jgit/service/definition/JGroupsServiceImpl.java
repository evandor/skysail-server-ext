package de.twenty11.skysail.server.osgi.jgit.service.definition;

import java.io.InputStream;
import java.io.OutputStream;

import org.jgroups.Address;
import org.jgroups.Channel;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

public class JGroupsServiceImpl implements JGroupsService, Receiver {

    private JChannel channel;

    @Override
    public Channel getChannel() {
        try {
            channel = new JChannel();
            channel.setReceiver(this);
            channel.connect("skysail cluster");
            return channel;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void send(Channel channel, String message) {
        Message msg = new Message(null, null, message);
        try {
            channel.send(msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setState(InputStream input) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    @Override
    public void suspect(Address suspected_mbr) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void block() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unblock() {
        // TODO Auto-generated method stub
        
    }
    
   

}
