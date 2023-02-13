package tw.elliot.db.it;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
@Profile("dev")
public class RepositoryTestConfig {
	public static RepoErrorEnum TEST_ERROR_ENUM = null;
}
