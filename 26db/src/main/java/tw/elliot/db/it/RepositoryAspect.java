package tw.elliot.db.it;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;

@Component
@Aspect
@Slf4j
@Profile("dev")
public class RepositoryAspect {

	public RepositoryAspect() {
		log.warn("You should not see me if we were not in dev.");
	}
	@Pointcut("this(org.springframework.data.repository.Repository)")
	public void pointcutRepo() {
	}

	@Before("pointcutRepo()")
	public void fa() throws SQLException, SocketException, IOException {
		switch (RepositoryTestConfig.TEST_ERROR_ENUM) {
			case SQL -> throw new SQLException("just test sql");
			case Network -> throw new SocketException("test socket");
			case IO -> throw new IOException("test io");
			case Null -> log.debug("do nothing");
			default -> log.debug("do nothing for test error");
		}
	}
}
