package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EchoMultiServer {

    private ServerSocket serverSocket;
    private List<EchoClientHandler2> clients = new ArrayList<>();

    public void start(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        while (true)
        {
            EchoClientHandler2 client = new EchoClientHandler2(serverSocket.accept());
            client.start();
            

        }
    }

    public void stop() throws IOException
    {
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException
    {
        EchoMultiServer server = new EchoMultiServer();
        server.start(9998);
    }
}
