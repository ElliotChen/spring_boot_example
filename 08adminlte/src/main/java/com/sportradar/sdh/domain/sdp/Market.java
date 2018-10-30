package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseMarket;
import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.IdCompositable;
import com.sportradar.sdh.domain.dgt.SportMarket;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Data
@NoArgsConstructor
public class Market extends BaseMarket {
	private List<com.sportradar.sdh.domain.dgt.SportMarket> dgtMarketXRefs = new ArrayList<>();

	private List<com.sportradar.sdh.domain.br.Market> brMarketXRefs = new ArrayList<>();

	private List<BaseMarket> referMarketXRefs = new ArrayList<>();

	public Market(Long marketId) {
		this(marketId, "", "");
	}

	public Market(Long marketId, String dgtSportMarketIds, String brMarketIds) {
		super();
		this.marketId = marketId;

		this.initDgtXRefs(dgtSportMarketIds);
		this.initBrXRefs(brMarketIds);

		/*
		referSportXRefs.clear();
		referSportXRefs.addAll(this.dgtMarketXRefs);
		referSportXRefs.addAll(this.brMarketXRefs);
		*/
	}

	public void initDgtXRefs(String dgtSportMarketIds) {
		String[] refSportIds = StringUtils.split(dgtSportMarketIds, ',');
		dgtMarketXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				com.sportradar.sdh.domain.dgt.SportMarket sportMarket = com.sportradar.sdh.domain.dgt.SportMarket.ofRefCompositeId(id, marketId);
				if (null != sportMarket) {
					dgtMarketXRefs.add(sportMarket);
				}
			}
		}
	}

	public void initBrXRefs(String brMarketIds) {
		String[] refSportIds = StringUtils.split(brMarketIds, ',');
		dgtMarketXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				com.sportradar.sdh.domain.br.Market market = com.sportradar.sdh.domain.br.Market.ofRefCompositeId(id, marketId);
				if (null != market) {
					brMarketXRefs.add(market);
				}
			}
		}
	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.marketId);
	}

	public String getDgtIdXRefs() {
		return IdCompositable.joinCompositedId(this.dgtMarketXRefs);
	}

	public String getBrIdXRefs() {
		return IdCompositable.joinCompositedId(this.brMarketXRefs);
	}
}
