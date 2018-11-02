package com.sportradar.sdh.dto.sdp;

import com.sportradar.sdh.domain.sdp.League;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LeagueDto extends League {
	private List<Translation> translations = new ArrayList<>();

	public String getLangString() {
		return  Translation.transToLangString(this.translations);
	}

	private com.sportradar.sdh.domain.dgt.League dgtLeague;

	private com.sportradar.sdh.domain.br.League brLeague;

	public String getDgtIdXRefs() {
		return dgtLeague !=null ? dgtLeague.getCompositedId() : "";
	}

	public String getBrIdXRefs() {
		return brLeague != null ? brLeague.getCompositedId() : "";
	}
}
