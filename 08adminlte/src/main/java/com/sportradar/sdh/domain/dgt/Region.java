package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseRegion;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class Region extends BaseRegion {

	private com.sportradar.sdh.domain.sdp.Region regionRef;

	public Region(Integer regionNum) {
		this.regionNum = regionNum;
	}

	public Region(Integer regionNum, String regionNumRef) {
		this.setRegionNum(regionNum);

		if (!"-1".equals(regionNum)) {

		}

	}

	public Region(Integer regionNum, Integer regionNumRef) {
		this.setRegionNum(regionNum);

		this.regionRef = new com.sportradar.sdh.domain.sdp.Region(regionNumRef);

	}

	public String getCompositedId() {
		return String.valueOf(this.regionNum);
	}

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
