package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private ExecutorService ex;
    private ServerSocket serverSocket;
    private SpectatorCounter spectatorCounter;
    private AtomicInteger numberOfTurnTiles;

    public Server()
    {
        this.ex = Executors.newCachedThreadPool();
        this.spectatorCounter = new SpectatorCounter();
        this.numberOfTurnTiles = new AtomicInteger(0);
    }

    public void startConnection(String ip, int port)
    {
        try
        {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));
            System.out.println("SERVER: SERVER CREATED!");
            System.out.println("SERVER: LISTENING ON: " + ip + " PORT: " + port);

            while (true)
            {
                System.out.println("SERVER: WAITING FOR CLIENTS");
                ServerClientThread client = new ServerClientThread(serverSocket.accept(), spectatorCounter, numberOfTurnTiles);
                ex.execute(client);
                System.out.println("SERVER: CLIENT CONNECTED");
            }
        } catch (IOException e)
        {
            System.out.println("SERVER: Could not listen on port: " + port + "...");
        }
    }

}
