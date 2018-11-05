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

	private com.sportradar.sdh.domain.dgt.MarketOption dgtMarketOption = new com.sportradar.sdh.domain.dgt.MarketOption();

	private com.sportradar.sdh.domain.br.MarketOption brMarketOption = new com.sportradar.sdh.domain.br.MarketOption();

	public String getDgtIdXRefs() {
		return dgtMarketOption !=null ? dgtMarketOption.getCompositedId() : "";
	}

	public String getBrIdXRefs() {
		return brMarketOption != null ? brMarketOption.getCompositedId() : "";
	}
}
