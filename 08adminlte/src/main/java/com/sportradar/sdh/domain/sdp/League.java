package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseLeague;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
public class League extends BaseLeague {

	private List<com.sportradar.sdh.domain.dgt.League> dgtLeagueXRefs = new ArrayList<>();

	private List<com.sportradar.sdh.domain.br.League> brLeagueXRefs = new ArrayList<>();

	private List<BaseLeague> referLeagueXRefs = new ArrayList<>();


	public League(Long leagueId) {
		this(leagueId, "", "");
	}

	public League(Long leagueId, String dgtLeagueIds, String brLeagueIds) {
		this.setSportId(leagueId);

		String[] refSportIds = org.apache.commons.lang3.StringUtils.split(dgtLeagueIds, ',');
		dgtLeagueXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				if (StringUtils.isNumeric(id) && !"-1".equals(id)) {
					dgtLeagueXRefs.add(new com.sportradar.sdh.domain.dgt.League(Long.parseLong(id), leagueId));
				}
			}
		}


		refSportIds = org.apache.commons.lang3.StringUtils.split(brLeagueIds, ',');
		brLeagueXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				if (StringUtils.isNumeric(id) && !"-1".equals(id)) {
					brLeagueXRefs.add(new com.sportradar.sdh.domain.br.League(Long.parseLong(id)));
				}
			}
		}

		referLeagueXRefs.clear();
		referLeagueXRefs.addAll(this.dgtLeagueXRefs);
		referLeagueXRefs.addAll(this.brLeagueXRefs);
	}
}
