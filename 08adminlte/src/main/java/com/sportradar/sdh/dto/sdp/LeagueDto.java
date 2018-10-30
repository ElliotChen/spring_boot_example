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
}
