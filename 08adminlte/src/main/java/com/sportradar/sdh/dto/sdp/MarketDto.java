package com.sportradar.sdh.dto.sdp;

import com.sportradar.sdh.domain.sdp.Market;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MarketDto extends Market {
	private List<Translation> translations = new ArrayList<>();

	public String getLangString() {
		return  Translation.transToLangString(this.translations);
	}
}
