package com.sportradar.sdh.domain.br;

import com.sportradar.sdh.domain.common.BaseMarketOption;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

		if (StringUtils.isEmpty(compositeKey)) { //  || compositeKey.startsWith("-1")) {
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
		return String.valueOf(this.marketId + " " + marketTypeId + " " + optionNum + " " + optionType);
	}

	public void setCompositedId(String compositedId) {
		String[] keys = compositedId.trim().split(" ");

		this.marketId = Long.parseLong(keys[0]);
		this.marketTypeId = Integer.parseInt(keys[1]);
		this.optionNum = Integer.parseInt(keys[2]);
		this.optionType = keys[3];
	}

	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.BR;
	}
}
