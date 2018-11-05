package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BasePeriod;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;

@Data
public class Period extends BasePeriod {
	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
