package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseMarketOption;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class MarketOption extends BaseMarketOption {
	private Integer marketTypeId;

	private String optionType;

	private com.sportradar.sdh.domain.sdp.MarketOption marketOptionRef;
	/*
	private Long marketId;

	private Integer optionNum;
	*/

	public MarketOption(Long marketId, Integer optionNum, Integer marketTypeId, String optionType) {
		this.setMarketId(marketId);
	}

	public static MarketOption ofRefCompositeId(String compositeKey, Long refMarketId, Integer refOptionNum) {
		MarketOption marketOption = null;

		if (StringUtils.isEmpty(compositeKey) || compositeKey.startsWith("-1")) {
			return marketOption;
		}

		String[] keys = compositeKey.trim().split(" ");

		marketOption = new MarketOption();
		marketOption.setMarketId(Long.parseLong(keys[0]));
		marketOption.setOptionNum(Integer.parseInt(keys[1]));
		marketOption.setMarketTypeId(Integer.parseInt(keys[2]));
		marketOption.setOptionType(keys[3]);


		marketOption.setMarketOptionRef(new com.sportradar.sdh.domain.sdp.MarketOption(refMarketId, refOptionNum));

		return marketOption;
	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.marketId + " " + optionNum + " " + marketTypeId + " " + optionType);
	}
}
