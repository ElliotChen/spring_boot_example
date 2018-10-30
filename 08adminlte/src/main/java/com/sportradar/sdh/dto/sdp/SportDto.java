package com.sportradar.sdh.dto.sdp;

import com.sportradar.sdh.domain.sdp.Sport;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SportDto extends Sport implements Serializable {
	private List<Translation> translations = new ArrayList<>();

	public String getLangString() {
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
