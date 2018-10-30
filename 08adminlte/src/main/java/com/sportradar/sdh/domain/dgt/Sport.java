package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseSport;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Sport extends BaseSport {

	private com.sportradar.sdh.domain.sdp.Sport sportRef;

	public Sport(Long sportId) {
		this.setSportId(sportId);
	}

	public Sport(Long sportId, String sportIdRef) {
		this.sportId = sportId;

		if (!"-1".equals(sportIdRef)) {

		}

	}

	public Sport(Long sportId, Long sportIdRef) {
		this.sportId = sportId;

		this.sportRef = new com.sportradar.sdh.domain.sdp.Sport(sportIdRef);

	}

	public String getCompositedId() {
		return String.valueOf(this.sportId);
	}
}
