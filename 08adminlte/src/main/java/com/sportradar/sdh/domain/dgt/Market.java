package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseMarket;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;

@Data
public class Market extends BaseMarket {

	protected String eventTypeId;

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
