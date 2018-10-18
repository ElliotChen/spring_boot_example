package tw.elliot.profile.conf;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class DevProfileConfigTest {

    @Value("${spring.profiles:unknown}")
    private String profile;

    @Test
    public void testProfileName() {
        Assert.assertEquals("dev", this.profile);
    }


}
