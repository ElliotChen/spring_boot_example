package com.sportradar.sdp.domain.dgt;

import com.sportradar.sdp.domain.common.BaseMarket;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Market")
@Data
public class Market extends BaseMarket {
	@Override
	public String getExpressId() {
		return this.getMarketId().toString();
	}

	@Override
	public String getIdXRefs() {
		return this.getMarketIdXRefs();
	}

	@Override
	public void setMergedIdXRefs(String mergedIdXRefs) {
	}
}
