package tw.elliot.log4j2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.elliot.log4j2.annotation.SensitiveData;

/**
 * @author elliot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

	@SensitiveData(type = SensitiveType.NAME)
	private String name;
	@SensitiveData(type = SensitiveType.ID)
	private String id;
	@SensitiveData(type = SensitiveType.PHONE)
	private String phone;
}
