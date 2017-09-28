package server;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        server.start("127.0.0.1", 9999);
    }

}
