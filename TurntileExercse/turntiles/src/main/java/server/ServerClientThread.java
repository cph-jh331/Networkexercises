package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private SpectatorCounter spectatorCounter;
    private AtomicInteger numberOfTurnTiles;

    public ServerClientThread(Socket clientSocket, SpectatorCounter turntileCounter, AtomicInteger numberOfTurnTiles)
    {
        this.clientSocket = clientSocket;
        this.spectatorCounter = turntileCounter;
        this.numberOfTurnTiles = numberOfTurnTiles;
    }

    @Override
    public void run()
    {
        try
        {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("turntile - you will become a turntile\n"
                    + "monitor - you will become a monitor");

            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                System.out.println("server receveiced: " + inputLine);
                if ("turntile".equals(inputLine.toLowerCase()))
                {
                    doTurnTileThings(out, in);
                    break;
                } else if ("monitor".equalsIgnoreCase(inputLine))
                {
                    doMonitorThings(out, in);
                    break;
                } else
                {
                    out.println("Invalid command");
                }

            }
        } catch (IOException ex)
        {
            Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void doTurnTileThings(PrintWriter out, BufferedReader in) throws IOException
    {
        setName("Turntile number: " + this.numberOfTurnTiles.incrementAndGet());
        out.println("I AM A TURNTILE NAMED: " + getName());
        out.println("count - to increment the counter");
        out.println("spectators - to see the current count");
        out.println("exit - to quit");
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            switch (inputLine.toLowerCase())
            {
                case "count":
                    spectatorCounter.count();
                    out.println("incremented spectators by one");
                    break;
                case "spectators":
                    out.println("Current number of spectators: " + spectatorCounter.getCount());
                    break;
                case "exit":
                    out.println("goodbye!");
                    this.numberOfTurnTiles.decrementAndGet();
                    System.out.println("client exited");
                    return;
                default:
                    out.println("invalid command");
                    break;
            }

        }
    }

    public void doMonitorThings(PrintWriter out, BufferedReader in) throws IOException
    {
        out.println("I AM A MONITOR");
        out.println("spectators - to see the current spectator count");
        out.println("exit - to quit");
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            switch (inputLine.toLowerCase())
            {
                case "spectators":
                    out.println("Current number of spectators: " + spectatorCounter.getCount());
                    break;
                case "exit":
                    out.println("goodbye!");
                    System.out.println("client exited");
                    return;
                default:
                    out.println("invalid command");
                    break;
            }

        }

    }

}
