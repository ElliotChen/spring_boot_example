package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseCompetitor;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Competitor extends BaseCompetitor {
	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.BR;
	}
}
