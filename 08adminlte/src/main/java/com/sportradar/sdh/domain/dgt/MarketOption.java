package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseMarketOption;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MarketOption extends BaseMarketOption {

	private com.sportradar.sdh.domain.sdp.MarketOption marketOptionRef;

	public MarketOption(Long marketId, Integer optionNum) {
		this.marketId = marketId;
		this.optionNum = optionNum;
	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.marketId + " " + optionNum);
	}

	@Override
	public void setCompositedId(String compositedId) {
		String[] keys = compositedId.trim().split(" ");

		this.marketId = Long.parseLong(keys[0]);
		this.optionNum = Integer.parseInt(keys[1]);
	}

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.DGT;
	}
}
