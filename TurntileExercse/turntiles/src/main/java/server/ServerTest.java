package server;

public class ServerTest {

    public static void main(String[] args)
    {
        Server server = new Server();
        server.startConnection("127.0.0.1", 9999);
    }

}
