package client;

public class ClientTest {

    public static void main(String[] args)
    {
        Client client = new Client();
        client.startConnection("127.0.0.1", 9999);
    }

}
