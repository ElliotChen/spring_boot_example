package tw.elliot.db.service;

import tw.elliot.db.model.User;

import java.util.Optional;

public interface DemoService {
	void createUser(String name);

	Optional<User> findUser(Long id);
}
