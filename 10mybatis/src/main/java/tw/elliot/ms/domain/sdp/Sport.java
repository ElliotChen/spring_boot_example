package tw.elliot.ms.domain.sdp;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import tw.elliot.ms.domain.common.BaseEntity;
import tw.elliot.ms.domain.common.BaseSport;
import tw.elliot.ms.domain.common.UpdateIdable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Sport extends BaseSport implements UpdateIdable {

	private List<tw.elliot.ms.domain.dgt.Sport> dgtSportXRefs = new ArrayList<>();

	private List<tw.elliot.ms.domain.br.Sport> brSportXRefs = new ArrayList<>();

	public Sport(Long sportId) {
		this(sportId, "", "");
	}

	public Sport(Long sportId, String dgtSportIds, String brSportIds) {
		this.setSportId(sportId);

		String[] refSportIds = StringUtils.split(dgtSportIds, ',');
		dgtSportXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				if (StringUtils.isNumeric(id) || !"-1".equals(id)) {
					dgtSportXRefs.add(new tw.elliot.ms.domain.dgt.Sport(Long.parseLong(id), sportId));
				}
			}
		}


		refSportIds = StringUtils.split(brSportIds, ',');
		brSportXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				if (StringUtils.isNumeric(id) || !"-1".equals(id)) {
					brSportXRefs.add(new tw.elliot.ms.domain.br.Sport(Long.parseLong(id)));
				}
			}
		}
	}
/*
	public Sport(Long sportId, Long sportIdRef) {
		this.setSportId(sportId);

		this.region = new tw.elliot.ms.domain.sdp.Sport();

	}

*/
	@Override
	public void updateId(String key) {
		this.setSportId(Long.parseLong(key));
	}
}
