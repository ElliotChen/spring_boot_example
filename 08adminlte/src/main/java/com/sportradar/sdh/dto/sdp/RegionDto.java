package com.sportradar.sdh.dto.sdp;

import com.sportradar.sdh.domain.sdp.Region;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegionDto extends Region {
	private List<Translation> translations = new ArrayList<>();

	public String getLangString() {
		return  Translation.transToLangString(this.translations);
	}

	private com.sportradar.sdh.domain.dgt.RegionSport dgtRegionSport;

	private com.sportradar.sdh.domain.br.RegionSport brRegionSport;

	public String getDgtIdXRefs() {
		return dgtRegionSport !=null ? dgtRegionSport.getCompositedId() : "";
	}

	public String getBrIdXRefs() {
		return brRegionSport != null ? brRegionSport.getCompositedId() : "";
	}
}
