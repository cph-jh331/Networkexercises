package server;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClientHandler2 extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private static HashMap<String, String> translateMap;

    public EchoClientHandler2(Socket socket)
    {
        this.clientSocket = socket;
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
        try
        {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                if ("exit".equals(inputLine))
                {
                    out.println("goodbye!");
                    break;
                }
                if (inputLine.length() != 0 && inputLine.contains("#"))
                {

                    int hash = inputLine.indexOf('#');

                    String firstString = inputLine.substring(0, hash).toUpperCase();
                    String endString = inputLine.substring(hash + 1);
                    System.out.println(firstString);
                    System.out.println(endString);
                    switch (firstString)
                    {
                        case "UPPER":
                            out.println(endString.toUpperCase());
                            break;
                        case "LOWER":
                            out.println(endString.toLowerCase());
                            break;
                        case "REVERSE":
                            out.println(new StringBuilder(endString).reverse().toString());
                            break;
                        case "TRANSLATE":
                            String translate = translateMap.get(endString);
                            if (translate == null)
                            {
                                out.println("#NOT_FOUND");
                            } else
                            {
                                out.println(translate);
                            }
                            break;
                        default:
                            out.println("invalid command");
                            break;
                    }
                } else
                {
                    out.println("invalid command");
                }

            }
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Socket getSocket(){
        return this.clientSocket;
    }
}
