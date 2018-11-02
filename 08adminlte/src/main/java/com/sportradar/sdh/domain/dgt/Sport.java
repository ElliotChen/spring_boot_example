package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class Sport extends BaseSport {

	private com.sportradar.sdh.domain.common.BaseSport sportRef = new com.sportradar.sdh.domain.sdp.Sport();

	public Sport(Long sportId) {
		this.setSportId(sportId);
	}


	public Sport(Long sportId, Long sportIdRef) {
		this.sportId = sportId;

		this.sportRef = new com.sportradar.sdh.domain.sdp.Sport(sportIdRef);

	}

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
