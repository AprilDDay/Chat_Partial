/*package ie.gmit.dip;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TestChatServer {

	@Test
	public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
	    ChatClient client = new ChatClient();
	    client.startClientConnection("192.168.1.3", 6666);
	    String response = client.sendMessage("hello server");
	    assertEquals("hello client", response);
	}
}
*/