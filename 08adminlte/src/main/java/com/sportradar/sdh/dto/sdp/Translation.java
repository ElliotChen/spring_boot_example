package com.sportradar.sdh.dto.sdp;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Translation {
	private Integer languageCode;

	private String languageName;

	private String translationValue;

	@Override
	public String toString() {
		return "["+languageName+"]: "+translationValue;
	}

	public static String transToLangString(List<Translation> translations) {
		String join = String.join(", ", translations.stream()
				.map(translation -> translation.toString())
				.collect(Collectors.toList())
		);

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		sb.append("]");
		return "["+join+"]";
	}
}
