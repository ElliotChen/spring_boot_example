package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public abstract class BaseEntity implements IdCompositable {
	protected Boolean isPreloaded = new Boolean(false);

	protected Language language = new Language(1,"English");

	protected Partner partner = new Partner(1,"QL");

	public String getCompositedId() {
		return "";
	}
	public void setCompositedId(String compositedId) {};


	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.UNKNOWN;
	}
}
