package tw.elliot.tcpserver.handler.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class DefaultEntryHandlerTest {

	@Test
	public void testReadEntryResult() {
		DefaultEntryHandler handler = new DefaultEntryHandler();
		handler.setEntryResultPath("./src/test/resources");
		handler.setCharEncoding("BIG5");
		handler.setFileExtension("txt");

		String handle = handler.handle("*00851193/D+");

		Assertions.assertEquals("OKOK!", handle);
	}
}