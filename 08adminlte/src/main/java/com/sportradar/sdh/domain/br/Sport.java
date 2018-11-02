package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sport extends BaseSport {

	protected com.sportradar.sdh.domain.sdp.Sport sportRef = new com.sportradar.sdh.domain.sdp.Sport();

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

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.BR;
	}
}
