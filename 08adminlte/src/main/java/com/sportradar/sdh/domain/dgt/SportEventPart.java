package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseSportEventPart;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;

@Data
public class SportEventPart extends BaseSportEventPart {
	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
