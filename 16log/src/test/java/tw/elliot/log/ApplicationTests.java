package tw.elliot.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

	@Test
	public void contextLoads() {
		MDC.put("userid", "123456");
		log.info("MDC TEST!!!");
	}

}

