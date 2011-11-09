package de.twenty11.skysail.server.osgi.jgit.service.definition;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

/**
 * example from http://www.jgroups.org/tutorial-3.x/html/ch02.html
 *
 */
public class SimpleChat implements Receiver {

    JChannel channel;
    String user_name = System.getProperty("user.name", "n/a");

    private void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();
                String line = in.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit"))
                    break;

                line = "[" + user_name + "] " + line;
                Message msg = new Message(null, null, line);
                channel.send(msg);
            }
            catch (Exception e) {
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new SimpleChat().start();
    }

    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }

    @Override
    public void getState(OutputStream arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setState(InputStream arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void block() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void suspect(Address arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unblock() {
        // TODO Auto-generated method stub
        
    }
}
