package com.sportradar.sdh.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketOptionPK implements Serializable {

	public MarketOptionPK(String expressId) {
		if (StringUtils.isEmpty(expressId) || !expressId.contains("-")) {
			return;
		}

		String[] strings = expressId.split("-");

		this.marketId = Long.valueOf(strings[0]);
		this.optionNum = Integer.valueOf(strings[1]);

	}
	protected Long marketId;

	protected Integer optionNum;
}
