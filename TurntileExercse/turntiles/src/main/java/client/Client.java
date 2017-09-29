package client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader consoleInput;
    private String input;

    public void startConnection(String ip, int port)
    {
        try
        {
            this.consoleInput = new BufferedReader(new InputStreamReader(System.in));

            try
            {
                this.clientSocket = new Socket(ip, port);
                this.out = new PrintWriter(clientSocket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            } catch (IOException ex)
            {
                System.out.println("CLIENT: UNABLE TO CONNECT TO SERVER");
                return;
            }
            try
            {
                MessageThread antiBlockMessage = new MessageThread(in);
                antiBlockMessage.start();
                while ((input = consoleInput.readLine()) != null)
                {
                    out.println(input);
                    try
                    {
                        Thread.sleep(400);
                    } catch (InterruptedException e)
                    {
                        System.out.println("Interrupted");
                    }
                    if (!antiBlockMessage.isAlive())
                    {
                        break;
                    }

                }
            } catch (IOException ex)
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            consoleInput.close();
            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
