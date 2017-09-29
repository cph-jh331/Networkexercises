package client;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerMessageThread extends Thread {

    private BufferedReader in;
    private static boolean isRunning;

    public ServerMessageThread(BufferedReader in, boolean isRunning)
    {
        this.isRunning = isRunning;
        this.in = in;

    }

    @Override
    public void run()
    {
        try
        {
            String stopBlockingMsg = "";
            while (isRunning)
            {
                if("goodbye!".equals(stopBlockingMsg)){
                    isRunning = false;
                    break; 
                }
                System.out.println(isRunning);
                if ((stopBlockingMsg = in.readLine()) != null)
                {
                    System.out.println(stopBlockingMsg);
                }
            }
        } catch (IOException e)
        {
            System.out.println("hvad fandens");

        }
    }

    public void setIsRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

}
