package tw.elliot.db.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import tw.elliot.db.repo.UserRepo;
import tw.elliot.db.service.DemoService;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DemoServiceImplTest {

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private DemoServiceImpl demoService;

	@Test
	void createUser() {

		Assert.notNull(demoService.getUserRepo(), "user repo should be injected");
		this.demoService.createUser("A");

		Mockito.verify(userRepo, Mockito.times(1)).save(Mockito.any());
	}
}