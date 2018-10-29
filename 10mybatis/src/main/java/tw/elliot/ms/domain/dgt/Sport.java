package tw.elliot.ms.domain.dgt;

import lombok.Data;
import lombok.NoArgsConstructor;
import tw.elliot.ms.domain.common.BaseEntity;
import tw.elliot.ms.domain.common.BaseSport;
import tw.elliot.ms.domain.common.UpdateIdable;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Sport extends BaseSport implements UpdateIdable {

	private tw.elliot.ms.domain.sdp.Sport sportRef;

	public Sport(Long sportId) {
		this.setSportId(sportId);
	}

	public Sport(Long sportId, String sportIdRef) {
		this.setSportId(sportId);

		if (!"-1".equals(sportIdRef)) {

		}

	}

	public Sport(Long sportId, Long sportIdRef) {
		this.setSportId(sportId);

		this.sportRef = new tw.elliot.ms.domain.sdp.Sport(sportIdRef);

	}

	@Override
	public void updateId(String key) {
		this.setSportId(Long.parseLong(key));
	}
}
