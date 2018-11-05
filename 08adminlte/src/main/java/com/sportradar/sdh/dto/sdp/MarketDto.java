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

	private com.sportradar.sdh.domain.dgt.SportMarket dgtSportMarket = new com.sportradar.sdh.domain.dgt.SportMarket();

	private com.sportradar.sdh.domain.br.Market brMarket = new com.sportradar.sdh.domain.br.Market();

	public String getDgtIdXRefs() {
		return dgtSportMarket !=null ? dgtSportMarket.getCompositedId() : "";
	}

	public String getBrIdXRefs() {
		return brMarket != null ? brMarket.getCompositedId() : "";
	}
}
