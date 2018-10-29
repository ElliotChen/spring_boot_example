package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseMarket;
import com.sportradar.sdh.domain.common.BaseSport;
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
	private List<com.sportradar.sdh.domain.dgt.SportMarket> dgtMarketXRefs;

	private List<com.sportradar.sdh.domain.br.Market> brMarketXRefs;

	private List<BaseMarket> referMarketXRefs = new ArrayList<>();

	public Market(Long marketId) {
		this(marketId, "", "");
	}

	public Market(Long marketId, String dgtSportIds, String brSportIds) {
		this.setMarketId(marketId);

		String[] refSportIds = org.apache.commons.lang3.StringUtils.split(dgtSportIds, ',');
		dgtMarketXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				SportMarket sportMarket = SportMarket.ofRefCompositeId(id, marketId);
				if (null != sportMarket) {
					dgtMarketXRefs.add(sportMarket);
				}
			}
		}

		refSportIds = StringUtils.split(brSportIds, ',');
		dgtMarketXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				com.sportradar.sdh.domain.br.Market market = com.sportradar.sdh.domain.br.Market.ofRefCompositeId(id, marketId);
				if (null != market) {
					brMarketXRefs.add(market);
				}
			}
		}

		/*
		referSportXRefs.clear();
		referSportXRefs.addAll(this.dgtMarketXRefs);
		referSportXRefs.addAll(this.brMarketXRefs);
		*/
	}
}
