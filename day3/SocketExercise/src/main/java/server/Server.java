package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    
    private ServerSocket serverSocket;
    private List<ServerClientThread> clients;
    private LinkedBlockingQueue<String> messages;
    private ServerChatThread chat;
    private ExecutorService ex = Executors.newFixedThreadPool(10);
    
    public Server()
    {
        this.clients = new ArrayList<>();
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
                chat = new ServerChatThread(clients, messages);
                ex.execute(chat);
                int counter = 1;
                while (true)
                {
                    ServerClientThread client = new ServerClientThread("" + counter, serverSocket.accept(), clients, messages);
                    clients.add(client);
                    ex.execute(client);
                    System.out.println("clients in main server thread: " + clients.size());
                    counter++;
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
    
    
    
}
