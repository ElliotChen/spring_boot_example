package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseCompetitor;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Competitor extends BaseCompetitor {

	private List<BaseCompetitor> competitorXRefs = new ArrayList<>();

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.SDP;
	}


	// competitorFullName
}
