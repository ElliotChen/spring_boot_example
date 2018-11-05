package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseLeagueGroup;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class LeagueGroup extends BaseLeagueGroup {
	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
