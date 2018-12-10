package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseCompetitor;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Competitor extends BaseCompetitor {
	private com.sportradar.sdh.domain.sdp.Competitor competitorRef = new com.sportradar.sdh.domain.sdp.Competitor();
	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.BR;
	}
}
