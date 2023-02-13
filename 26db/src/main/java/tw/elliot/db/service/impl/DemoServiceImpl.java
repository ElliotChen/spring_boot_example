package tw.elliot.db.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.elliot.db.model.User;
import tw.elliot.db.repo.UserRepo;
import tw.elliot.db.service.DemoService;

import java.util.Optional;

@Service
@Data
public class DemoServiceImpl implements DemoService {
	@Autowired
	private UserRepo userRepo;
	@Override
	public void createUser(String name) {
		User user = new User();
		user.setName(name);

		this.userRepo.save(user);
	}

	@Override
	public Optional<User> findUser(Long id) {
		return this.userRepo.findById(id);
	}
}
