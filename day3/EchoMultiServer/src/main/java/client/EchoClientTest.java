
package client;

import java.io.IOException;

public class EchoClientTest {

    public static void main(String[] args) throws IOException
    {
        EchoClient client = new EchoClient();
        client.startConnection("127.0.0.1", 9998);
        client.sendMessage("REVERSE#fisken");
    }
    
}
