package de.twenty11.skysail.server.ext.osgi.monitor.gogo.commands;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;

@Component(immediate = true, properties = { "osgi.command.scope=ohb", "osgi.command.function:String=on|off" })
public class HeartbeatCommands implements OsgiHeartbeatCommands {

    private SocketChannel socketChannel;

    @Activate
    public void activate() {
//        try {
//            NioClient client = new NioClient(InetAddress.getByName("127.0.0.1"), 9997);
//            Thread t = new Thread(client);
//            t.setDaemon(true);
//            t.start();
//            ResponseHandler handler = new ResponseHandler();
//            client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
//            handler.waitForResponse();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Deactivate
    public void deactivate() {
        if (socketChannel != null) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
    }

    @Override
    public void on() {
        System.out.println("switching OSGi heartbeat on");

        if (socketChannel != null) {
            String newData = "New String to write to file..." + System.currentTimeMillis();

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while (buf.hasRemaining()) {
                try {
                    socketChannel.write(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        // OsgiMonitorCallback.setOn();
    }

    @Override
    public void off() {
        System.out.println("switching OSGi heartbeat off");

        if (socketChannel != null) {
            String newData = "off";

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while (buf.hasRemaining()) {
                try {
                    socketChannel.write(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
