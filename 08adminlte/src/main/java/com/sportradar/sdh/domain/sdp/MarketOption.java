package com.sportradar.sdh.domain.sdp;

import java.util.ArrayList;
import java.util.List;

import com.sportradar.sdh.domain.common.BaseMarketOption;
import com.sportradar.sdh.domain.common.IdCompositable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class MarketOption extends BaseMarketOption {

	private List<com.sportradar.sdh.domain.dgt.MarketOption> dgtMarketOptionXRefs = new ArrayList<>();

	private List<com.sportradar.sdh.domain.br.MarketOption> brMarketOptionXRefs = new ArrayList<>();

	private List<BaseMarketOption> referMarketOptionXRefs = new ArrayList<>();

	public MarketOption(Long marketId) {
		this(marketId, -1);
	}
	public MarketOption(Long marketId, Integer optionNum) {
		this(marketId, optionNum, "", "");
	}

	public MarketOption(Long marketId, Integer optionNum, String dgtMarketOptionIds, String brMarketOptionIds) {
		this.setMarketId(marketId);
		this.setOptionNum(optionNum);

		this.initDgtXRefs(dgtMarketOptionIds);
		this.initBrXRefs(brMarketOptionIds);


		referMarketOptionXRefs.clear();
		referMarketOptionXRefs.addAll(this.dgtMarketOptionXRefs);
		referMarketOptionXRefs.addAll(this.brMarketOptionXRefs);

	}

	public void initDgtXRefs(String dgtMarketOptionIds) {
		String[] refSportIds = StringUtils.split(dgtMarketOptionIds, ',');
		dgtMarketOptionXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				com.sportradar.sdh.domain.dgt.MarketOption marketOption = com.sportradar.sdh.domain.dgt.MarketOption.ofRefCompositeId(id, marketId, optionNum);
				if (null != marketOption) {
					dgtMarketOptionXRefs.add(marketOption);
				}
			}
		}
	}

	public void initBrXRefs(String brMarketOptionIds) {
		String[] refSportIds = StringUtils.split(brMarketOptionIds, ',');
		brMarketOptionXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				com.sportradar.sdh.domain.br.MarketOption marketOption = com.sportradar.sdh.domain.br.MarketOption.ofRefCompositeId(id, marketId, optionNum);
				if (null != marketOption) {
					brMarketOptionXRefs.add(marketOption);
				}
			}
		}
	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.marketId + " " +this.optionNum);
	}

	public String getDgtIdXRefs() {
		return IdCompositable.joinCompositedId(this.dgtMarketOptionXRefs);
	}

	public String getBrIdXRefs() {
		return IdCompositable.joinCompositedId(this.brMarketOptionXRefs);
	}
}
