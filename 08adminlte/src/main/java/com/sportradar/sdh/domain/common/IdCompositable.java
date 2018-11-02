package com.sportradar.sdh.domain.common;

import java.util.List;
import java.util.stream.Collectors;

public interface IdCompositable {
	String getCompositedId();

	void setCompositedId(String compositedId);

	SourceTypeEnum getSourceType();
	static String joinCompositedId(List<? extends IdCompositable> list) {
		String join = String.join(", ", list.stream()
				.map(idCompositable -> idCompositable.getCompositedId())
				.collect(Collectors.toList())
		);
		return join;
	}
}
