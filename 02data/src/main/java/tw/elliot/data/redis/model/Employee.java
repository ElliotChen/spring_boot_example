package tw.elliot.data.redis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("employees")
public class Employee {
	@Id
	private Long id;

	private String firstname;

	@Indexed
	private String lastname;
}
