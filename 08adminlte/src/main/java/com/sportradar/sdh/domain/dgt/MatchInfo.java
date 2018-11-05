package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseMatchInfo;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
public class MatchInfo extends BaseMatchInfo {
	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
