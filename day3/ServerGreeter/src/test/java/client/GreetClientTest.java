/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bloch
 */
public class GreetClientTest {
    
    public GreetClientTest()
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
    public void testStartConnection() throws Exception
    {
        GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", 9999);
        String response = client.sendMessage("hello server");
        assertEquals(response, "hello client");
    }

    
}
