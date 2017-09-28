package client;

import java.io.IOException;

public class ClientTest {

    public static void main(String[] args) throws IOException
    {
        Client client = new Client();
        client.startConnection("127.0.0.1", 9999);
    }

}
