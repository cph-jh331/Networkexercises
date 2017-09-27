package server;

import java.net.*;
import java.io.*;
import java.util.Calendar;

public class TCPTime {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public void start(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        //bare fjern while, for kun at få tiden en gang, også luk den bagefter.
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            if ("exit".equals(inputLine))
            {
                out.println("goodbye!");
                break;
            }
            out.println(Calendar.getInstance().getTime());
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
        TCPTime time = new TCPTime();
        time.start(10000);
    }

}
