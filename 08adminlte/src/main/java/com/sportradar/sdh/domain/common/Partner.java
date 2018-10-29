package com.sportradar.sdh.domain.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Partner {
	@Id
	private Integer partnerId;

	private String partnerName;

	public Partner(Integer partnerId, String partnerName) {
		this.partnerId = partnerId;
		this.partnerName = partnerName;
	}
}
