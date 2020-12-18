package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @see com.sportradar.sdh.dao.sdp.handler.SportsHandler#buildSports
 */
@Data
@Slf4j
@NoArgsConstructor
public class Sport extends BaseSport {
	protected List<BaseSport> sportXRefs = new ArrayList<>();

	public Sport(Long sportId) {
		super();
		this.sportId = sportId;
	}

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.SDP;
	}
}
