package server;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoMultiServer {

    private ServerSocket serverSocket;

    public void start(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        while (true)
        {
            new EchoClientHandler2(serverSocket.accept()).start();
        }
    }

    public void stop() throws IOException
    {
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException
    {
        EchoMultiServer server = new EchoMultiServer();
        server.start(9998);
    }

    //why static? Why have it in here even?
    /*
    private static class EchoClientHandler extends Thread {

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket)
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
    */
}
