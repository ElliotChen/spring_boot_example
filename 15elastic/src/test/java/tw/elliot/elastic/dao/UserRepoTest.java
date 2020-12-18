package tw.elliot.elastic.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tw.elliot.elastic.domain.User;

import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepoTest {

	@Autowired
	private UserRepo userRepo;
	@Test
	public void test() {
		Assertions.assertNotNull(this.userRepo);
		User user = new User();
		user.setId(String.valueOf(System.currentTimeMillis()));
		user.setName("uts");

		this.userRepo.save(user);
	}

	@Test
	public void testFind() {
		List<User> users = this.userRepo.findByName("uts");
		Assertions.assertFalse(users.isEmpty());
	}
}