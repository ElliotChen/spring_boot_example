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
@ActiveProfiles("dev")
public class DevProfileConfigTest {

	@Value("${spring.profiles:unknown}")
	private String profile;

	@Value("${spring.application.name:unknown}")
	private String name;
	@Test
	public void testProfileName() {

		/*測試共用區的設定有效*/
		Assertions.assertEquals("06profile", this.name);

		/*測試各別區的設定有效*/
		Assertions.assertEquals("dev", this.profile);

	}


}
