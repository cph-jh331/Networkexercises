package client;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bloch
 */
public class EchoClientTest {

    public EchoClientTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Test
    public void client1test() throws IOException
    {
        EchoClient client1 = new EchoClient();
        client1.startConnection("127.0.0.1", 9998);
        String msg1 = client1.sendMessage("bob");
        String msg2 = client1.sendMessage("fisk");
        String msg3 = client1.sendMessage("exit");

        assertEquals("BOB", msg1);
        assertEquals("FISK", msg2);
        assertEquals("goodbye!", msg3);
    }

    @Test
    public void client2test() throws IOException
    {
        EchoClient client2 = new EchoClient();
        client2.startConnection("127.0.0.1", 9998);
        String msg1 = client2.sendMessage("bob");
        String msg2 = client2.sendMessage("fisk");
        String msg3 = client2.sendMessage("exit");

        assertEquals("BOB", msg1);
        assertEquals("FISK", msg2);
        assertEquals("goodbye!", msg3);
    }

}
