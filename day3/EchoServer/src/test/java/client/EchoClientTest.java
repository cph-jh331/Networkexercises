package client;

import java.io.IOException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class EchoClientTest {

    public EchoClientTest()
    {
    }

    private EchoClient client;

    @Before
    public void setup() throws IOException
    {
        client = new EchoClient();
        client.startConnection("127.0.0.1", 9997);

    }

    @After
    public void tearDown() throws IOException
    {
        client.stopConnection();
    }

    /**
     * Test of startConnection method, of class EchoClient.
     */
    @Test
    public void testStartConnection() throws Exception
    {
        String resp1 = client.sendMessage("HELLO!");
        String resp2 = client.sendMessage("bob");
        String resp3 = client.sendMessage("fisk");
        String resp4 = client.sendMessage("exit");
        
        assertEquals("HELLO!", resp1);
        assertEquals("BOB", resp2);
        assertEquals("FISK", resp3);
        assertEquals("goodbye", resp4);
    }
}
