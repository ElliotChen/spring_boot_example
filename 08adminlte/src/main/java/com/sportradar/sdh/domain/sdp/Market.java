package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseEntity;
import com.sportradar.sdh.domain.common.BaseMarket;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Market extends BaseMarket {

	protected String eventTypeId;

	private List<BaseEntity> marketXRefs = new ArrayList<>();

	@Override
	public String getCompositedId() {
		return String.valueOf(this.marketId);
	}

	public Market(Long marketId) {
		this.marketId = marketId;
	}

	/*
	public Market(Long marketId) {
		this(marketId, "", "");
	}

	public Market(Long marketId, String dgtSportMarketIds, String brMarketIds) {
		super();
		this.marketId = marketId;

		this.initDgtXRefs(dgtSportMarketIds);
		this.initBrXRefs(brMarketIds);


		sportXRefs.clear();
		sportXRefs.addAll(this.dgtMarketXRefs);
		sportXRefs.addAll(this.brMarketXRefs);

	}

	private List<com.sportradar.sdh.domain.dgt.SportMarket> dgtMarketXRefs = new ArrayList<>();

	private List<com.sportradar.sdh.domain.br.Market> brMarketXRefs = new ArrayList<>();

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

	public String getDgtIdXRefs() {
		return IdCompositable.joinCompositedId(this.dgtMarketXRefs);
	}

	public String getBrIdXRefs() {
		return IdCompositable.joinCompositedId(this.brMarketXRefs);
	}

	*/



}
