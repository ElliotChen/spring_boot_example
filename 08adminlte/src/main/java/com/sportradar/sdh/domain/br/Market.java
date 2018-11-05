package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseMarket;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@NoArgsConstructor
public class Market extends BaseMarket {
	private Integer marketTypeId;

	private com.sportradar.sdh.domain.sdp.Market marketRef;

	public Market(Long marketId, Integer marketTypeId, Long refMarketId) {
		this.setMarketId(marketId);
		this.setMarketTypeId(marketTypeId);

		this.marketRef = new com.sportradar.sdh.domain.sdp.Market(refMarketId);
	}


	public static Market ofRefCompositeId(String compositeKey, Long refMarketId) {
		Market market = null;

		if (StringUtils.isEmpty(compositeKey)) { //  || compositeKey.startsWith("-1")) {
			return market;
		}

		String[] keys = compositeKey.split(" ");

		market = new Market();
		market.setMarketId(Long.parseLong(keys[0]));
		market.setMarketTypeId(Integer.parseInt(keys[1]));

		market.setMarketRef(new com.sportradar.sdh.domain.sdp.Market(refMarketId));

		return market;
	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.marketId + " " + marketTypeId);
	}

	@Override
	public void setCompositedId(String compositedId) {
		String[] ids = compositedId.split(" ");
		this.marketId = Long.parseLong(ids[0]);
		this.marketTypeId = Integer.parseInt(ids[1]);
	}

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.BR;
	}
}
