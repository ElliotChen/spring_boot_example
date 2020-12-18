package com.sportradar.sdh.dto.sdp;

import com.sportradar.sdh.domain.sdp.Sport;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SportDto extends Sport implements Serializable {
	private List<Translation> translations = new ArrayList<>();

	public String getLangString() {
		return  Translation.transToLangString(this.translations);
	}

	private com.sportradar.sdh.domain.dgt.Sport dgtSport;

	private com.sportradar.sdh.domain.br.Sport brSport;

	public String getDgtIdXRefs() {
		return dgtSport!=null ? dgtSport.getCompositedId() : "";
	}

	public String getBrIdXRefs() {
		return brSport != null ? brSport.getCompositedId() : "";
	}

}
