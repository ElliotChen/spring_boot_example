package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseLeague;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
public class League extends BaseLeague {

	private com.sportradar.sdh.domain.sdp.League leagueRef;

	public League(Long leagueId) {
		this.setLeagueId(leagueId);
	}

	public League(Long leagueId, String leagueIdRef) {
		this.setLeagueId(leagueId);

		if (!"-1".equals(leagueIdRef)) {

		}

	}

	public League(Long leagueId, Long leagueIdRef) {
		this.setLeagueId(leagueId);

		this.leagueRef = new com.sportradar.sdh.domain.sdp.League(leagueIdRef);

	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.leagueId);
	}
}
