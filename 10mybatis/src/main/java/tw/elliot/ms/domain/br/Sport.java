package tw.elliot.ms.domain.br;

import lombok.Data;
import lombok.NoArgsConstructor;
import tw.elliot.ms.domain.common.BaseEntity;
import tw.elliot.ms.domain.common.BaseSport;
import tw.elliot.ms.domain.common.UpdateIdable;

@Data
@NoArgsConstructor
public class Sport extends BaseSport implements UpdateIdable {

	public Sport(Long sportId) {
		this.setSportId(sportId);
	}

	@Override
	public void updateId(String key) {
		this.setSportId(Long.parseLong(key));
	}
}
