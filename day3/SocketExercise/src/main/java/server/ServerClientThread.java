package server;

import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerClientThread extends Thread {

    private String name;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static Map<String, String> translateMap;
    private List<ServerClientThread> clients;
    private LinkedBlockingQueue<String> messages;

    public ServerClientThread(String name, Socket socket, List<ServerClientThread> clients, LinkedBlockingQueue<String> messages)
    {
        this.name = name;
        this.clients = clients;
        this.clientSocket = socket;
        this.messages = messages;
        if (translateMap == null)
        {
            translateMap = new HashMap<>();
            translateMap.put("hund", "dog");
            translateMap.put("kat", "cat");
            translateMap.put("fisk", "fish");
            translateMap.put("fugl", "bird");
        }
    }

    @Override
    public void run()
    {
        System.out.println("SERVER: NEW THREAD STARTED!");
        try
        {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Welcome...\n"
                    + "\tTIME# - to see the time.\n"
                    + "\tUPPER#text - to convert text to uppercase.\n"
                    + "\tLOWER#text - to convert text to lowercase.\n"
                    + "\tREVERSE#text - to reverse the text.\n"
                    + "\tTRANSLATE#text - to translate a word.\n"
                    + "\tMSGALL#text - to send a message to all clients.\n"
                    + "\tEXIT# - to exit the server.\n");
            String inputLine;

            while ((inputLine = in.readLine()) != null)
            {
                if (clientSocket.isClosed())
                {
                    System.out.println("hvad fanden");
                    break;
                }

                System.out.println("SERVER: RECEIVED INPUT - " + inputLine);
                if (inputLine.contains("#") && inputLine.length() > 0)
                {
                    int hash = inputLine.indexOf('#');
                    String commandStr = inputLine.substring(0, hash).toUpperCase();
                    String wordStr = inputLine.substring(hash + 1);

                    switch (commandStr)
                    {
                        case "TIME":
                            System.out.println("SERVER: CLIENT ASKED FOR TIME");
                            out.println(Calendar.getInstance().getTime().toString());
                            break;
                        case "UPPER":
                            System.out.println("SERVER: CLIENT ASKED FOR UPPERCASSED");
                            out.println(wordStr.toUpperCase());
                            break;
                        case "LOWER":
                            System.out.println("SERVER: CLIENT ASKED FOR LOWERCASE");
                            out.println(wordStr.toLowerCase());
                            break;
                        case "REVERSE":
                            System.out.println("SERVER: CLIENT ASKED FOR REVERSE");
                            out.println(new StringBuilder(wordStr).reverse().toString());
                            break;
                        case "TRANSLATE":
                            System.out.println("SERVER: CLIENT ASKED FOR TRANSLATE");
                            String translate = translateMap.get(wordStr);
                            if (translate == null)
                            {
                                out.println("#NOT_FOUND");
                            } else
                            {
                                out.println(translate);
                            }
                            break;
                        case "EXIT":
                            System.out.println("SERVER: CLIENT EXITED!");
                            out.println("goodbye!");
                            clients.remove(this);
                            return;
                        case "MSGALL":
                            sendMessageToAll(this.name + " yells " + wordStr.toUpperCase());
                            break;
                        default:
                            out.println("invalid command");
                            break;
                    }

                } else
                {
                    out.println("Commands use # and are greater than 0");
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
                System.out.println("røv2");
            }
        }
    }

    public void sendMessageToAll(String msg)
    {
        try
        {
            messages.put(msg);
        } catch (InterruptedException ex)
        {
            System.out.println(this.name + " got interrupted in putting a message to all");
        }
    }

    public void getMessage(String msg)
    {
        out.println(msg);
    }

}
