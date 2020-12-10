package tw.elliot.db.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.elliot.db.model.User;
import tw.elliot.db.repo.UserRepo;
import tw.elliot.db.service.DemoService;

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
}
