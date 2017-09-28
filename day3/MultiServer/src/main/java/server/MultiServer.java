package server;

import java.net.*;
import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiServer {

    private ServerSocket serverSocket;
    private BlockingQueue<ServerClientThread> clientThreads;
    private LinkedBlockingQueue<String> messages;

    public MultiServer()
    {
        this.clientThreads = new ArrayBlockingQueue<>(10);
        this.messages = new LinkedBlockingQueue<>(10);
    }

    public void start(String ip, int port) throws IOException
    {
        try
        {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));
            System.out.println("SERVER: SERVER CREATED");
            System.out.println("SERVER: LISTENING ON PORT: " + port + "...");
            try
            {
                System.out.println("SERVER: WAITING FOR CONNECTIONS...");
                while (true)
                {
                    ServerClientThread thread = new ServerClientThread(serverSocket.accept(), clientThreads);
                    thread.start();
                    clientThreads.put(thread);

                }
            } catch (Exception e)
            {
                System.out.println("SERVER: SOMETHING WENT HORRIBLY WRONG!");
            }
        } catch (IOException e)
        {
            System.out.println("SERVER: Could not listen on port: " + port + "...");
        }
    }

    public void stop() throws IOException
    {
        serverSocket.close();
    }

}
