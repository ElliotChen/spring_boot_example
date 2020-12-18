package tw.elliot.profile.conf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("xxxx")
public class UnknownProfileConfigTest {
	@Value("${spring.profiles:unknown}")
	private String profile;

	@Test
	public void testProfileName() {
		Assertions.assertEquals("unknown", this.profile);
	}
}
