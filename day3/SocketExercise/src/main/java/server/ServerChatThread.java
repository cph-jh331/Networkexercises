package server;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerChatThread extends Thread {

    private List<ServerClientThread> clients;
    private LinkedBlockingQueue<String> messages;

    public ServerChatThread(List<ServerClientThread> clients, LinkedBlockingQueue<String> messages)
    {
        this.clients = clients;
        this.messages = messages;
    }

    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("clients in serverchatthread: " + clients.size());
            try
            {
                String message = messages.take();
                for (ServerClientThread client : clients)
                {
//                    if (!client.isAlive())
//                    {
//                        clients.remove(client);
//                    }
                    System.out.println("sending to all clients...");
                    System.out.println(message + " in serverChatThread");
                    client.getMessage(message);
                }
            } catch (InterruptedException e)
            {
                System.out.println("ServerChatThread got interupted when trying to take message");
            }
        }
    }

}
