package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseRegion;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Region extends BaseRegion {

	private com.sportradar.sdh.domain.sdp.Region region;

	public Region(Integer regionNum) {
		this.setRegionNum(regionNum);
	}

	public Region(Integer regionNum, String regionNumRef) {
		this.setRegionNum(regionNum);

		if (!"-1".equals(regionNum)) {

		}

	}

	public Region(Integer regionNum, Integer regionNumRef) {
		this.setRegionNum(regionNum);

		this.region = new com.sportradar.sdh.domain.sdp.Region(regionNumRef);

	}
}
