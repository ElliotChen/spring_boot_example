package tw.elliot.elastic.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.elliot.elastic.domain.User;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {

	@Autowired
	private UserRepo userRepo;
	@Test
	public void test() {
		Assert.assertNotNull(this.userRepo);
		User user = new User();
		user.setId(String.valueOf(System.currentTimeMillis()));
		user.setName("uts");

		this.userRepo.save(user);
	}

	@Test
	public void testFind() {
		List<User> users = this.userRepo.findByName("uts");
		Assert.assertFalse(users.isEmpty());
	}
}