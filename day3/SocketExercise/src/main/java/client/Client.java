package client;

import java.io.*;
import java.net.*;

public class Client {
    
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader consoleInput;
    private String input;
    private static boolean isRunning = true;

    //private ExecutorService ex = Executors.newFixedThreadPool(10);
    public void startConnection(String ip, int port) throws IOException
    {
        this.consoleInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
        } catch (Exception e)
        {
            System.out.println("CLIENT: UNABLE TO CONNECT TO SERVER");
            return;
        }
        ServerMessageThread messageAntiBlock = new ServerMessageThread(in, isRunning);
        messageAntiBlock.start();
        System.out.println("CLIENT: CONNECTED TO SERVER");
        System.out.println("CLIENT: enter something...");
        while ((input = consoleInput.readLine()) != null)
        {
            out.println(input);
            try
            {
                Thread.sleep(400);
            } catch (InterruptedException e)
            {
                System.out.println("interrupted");
            }
            if (!messageAntiBlock.isAlive())
            {
                break;
            }
            //output = in.readLine();
            //System.out.println("SERVER MESSAGE: " + output);
//            if (output.equals("goodbye!"))
//            {
//                System.out.println("CLIENT LEFT THE SERVER");
//                break;
//            }

        }
        consoleInput.close();
        in.close();
        out.close();
        clientSocket.close();
    }
    
}
