package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseRegionSport;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import com.sportradar.sdh.domain.sdp.Region;

@Data
@NoArgsConstructor
public class RegionSport extends BaseRegionSport {

	private Region regionRef;

	public RegionSport(Integer regionNum, Long sportId) {
		this.setRegionNum(regionNum);
		this.setSportId(sportId);
	}

	public static RegionSport ofRefCompositeId(String compositeKey, Integer refRegionNum) {
		RegionSport regionSport = null;

		if (StringUtils.isEmpty(compositeKey)) { //  || "-1 -1".equals(compositeKey)) {
			return regionSport;
		}

		String[] keys = compositeKey.split(" ");

		regionSport = new RegionSport(Integer.parseInt(keys[0]), Long.parseLong(keys[1]));

		regionSport.setRegionRef(new Region(refRegionNum));

		return regionSport;
	}

	public String getCompositedId() {
		return String.valueOf(this.regionNum)+" "+String.valueOf(this.sportId);
	}
}
