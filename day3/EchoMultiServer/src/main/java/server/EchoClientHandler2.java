package server;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClientHandler2 extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public EchoClientHandler2(Socket socket)
    {
        this.clientSocket = socket;
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
                out.println(inputLine.toUpperCase());
            }
        } catch (IOException ex)
        {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
