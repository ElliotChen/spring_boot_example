package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseSport;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Sport")
@Data
public class Sport extends BaseSport {

	private com.sportradar.sdh.domain.sdp.Sport sportRef;

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

		this.sportRef = new com.sportradar.sdh.domain.sdp.Sport(sportIdRef);

	}
}
