package com.sportradar.sdh.dto.sdp;

import com.sportradar.sdh.domain.sdp.MarketOption;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MarketOptionDto extends MarketOption {
	private List<Translation> translations = new ArrayList<>();

	public String getLangString() {
		return  Translation.transToLangString(this.translations);
	}
}
