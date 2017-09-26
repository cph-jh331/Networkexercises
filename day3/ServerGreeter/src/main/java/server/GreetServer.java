package server;

import java.net.*;
import java.io.*;

public class GreetServer {
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public void start(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
        if ("hello server".equals(greeting))
        {
            out.println("hello client");
        } else
        {
            out.println("GREETING DOES NOT COMPUTE");
        }
    }
    
    public void stop() throws IOException
    {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException
    {
        GreetServer server = new GreetServer();
        server.start(9999);
    }
}
