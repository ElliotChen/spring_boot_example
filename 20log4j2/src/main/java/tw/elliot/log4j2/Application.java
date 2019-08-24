package tw.elliot.log4j2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.error("System out !!!");
		log.error("-----Test log4j2!------");
		SpringApplication.run(Application.class, args);
	}

}
