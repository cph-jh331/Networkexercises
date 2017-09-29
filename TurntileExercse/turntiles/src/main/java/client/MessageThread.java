package client;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageThread extends Thread {

    private BufferedReader in;

    public MessageThread(BufferedReader in)
    {
        this.in = in;
    }

    @Override
    public void run()
    {
        try
        {
            String stopBlockingMsg = "";
            while (true)
            {
                if ("goodbye!".equals(stopBlockingMsg))
                {
                    break;
                }
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
}
