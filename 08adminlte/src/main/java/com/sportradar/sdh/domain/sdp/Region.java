package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseRegion;
import com.sportradar.sdh.domain.common.BaseRegionSport;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
public class Region extends BaseRegion {

	private List<com.sportradar.sdh.domain.dgt.RegionSport> dgtRegionXRefs = new ArrayList<>();;

	private List<com.sportradar.sdh.domain.br.RegionSport> brRegionXRefs = new ArrayList<>();;


	private List<BaseRegionSport> referRegionXRefs = new ArrayList<>();

	public Region(Integer regionNum) {
		this(regionNum, "", "");
	}

	public Region(Integer regionNum, String dgtRegionNums, String brRegionNums) {
		this.setRegionNum(regionNum);

		String[] refRegionIds = org.apache.commons.lang3.StringUtils.split(dgtRegionNums, ',');
		dgtRegionXRefs.clear();
		if (null != refRegionIds) {
			for (String id : refRegionIds) {
				com.sportradar.sdh.domain.dgt.RegionSport regionSport = com.sportradar.sdh.domain.dgt.RegionSport.ofRefCompositeId(id, regionNum);
				if (null != regionSport) {
					dgtRegionXRefs.add(regionSport);
				}
			}
		}


		refRegionIds = org.apache.commons.lang3.StringUtils.split(brRegionNums, ',');
		brRegionXRefs.clear();
		if (null != refRegionIds) {
			for (String id : refRegionIds) {
				com.sportradar.sdh.domain.br.RegionSport regionSport = com.sportradar.sdh.domain.br.RegionSport.ofRefCompositeId(id, regionNum);
				if (null != regionSport) {
					brRegionXRefs.add(regionSport);
				}
			}
		}

		referRegionXRefs.clear();
		referRegionXRefs.addAll(this.dgtRegionXRefs);
		referRegionXRefs.addAll(this.brRegionXRefs);
	}
}
