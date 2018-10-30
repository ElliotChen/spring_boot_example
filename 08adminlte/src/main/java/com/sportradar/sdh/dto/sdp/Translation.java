package com.sportradar.sdh.dto.sdp;

import lombok.Data;

@Data
public class Translation {
	private Integer languageCode;

	private String languageName;

	private String translationValue;

	@Override
	public String toString() {
		return "("+languageCode+")["+languageName+"]: "+translationValue;
	}

}
