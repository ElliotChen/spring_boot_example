package tw.elliot.db.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.elliot.db.it.RepoErrorEnum;
import tw.elliot.db.it.RepositoryTestConfig;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backdoor")
@Profile("dev")
public class BackdoorCtrl {

	@GetMapping(path = "/setrepoerror/{errorenum}", produces = "application/json")
	public RepoErrorEnum setRepoError(@PathVariable RepoErrorEnum errorenum) {
		RepositoryTestConfig.TEST_ERROR_ENUM = errorenum;
		return RepositoryTestConfig.TEST_ERROR_ENUM;
	}
}
