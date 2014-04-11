package de.twenty11.skysail.server.ext.osgi.monitor.agent.channel;

import java.nio.channels.SocketChannel;

public class ServerDataEvent {
    public CommandListener server;
    public SocketChannel socket;
    public byte[] data;

    public ServerDataEvent(CommandListener server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}
