package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseEventType;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class EventType extends BaseEventType {

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
