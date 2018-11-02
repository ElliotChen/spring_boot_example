package com.sportradar.sdh.domain.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Partner {
	protected Integer partnerId;

	protected String partnerName;

	public Partner(Integer partnerId, String partnerName) {
		this.partnerId = partnerId;
		this.partnerName = partnerName;
	}
}
