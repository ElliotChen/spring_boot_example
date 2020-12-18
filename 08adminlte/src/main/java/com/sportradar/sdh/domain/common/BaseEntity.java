package com.sportradar.sdh.domain.common;

import lombok.Data;

@Data
public abstract class BaseEntity implements IdCompositable {
	protected Boolean isPreloaded = Boolean.FALSE;

	protected Language language = new Language(1,"English");

	protected Partner partner = new Partner(1,"QL");

	@Override
	public String getCompositedId() {
		return "";
	}

	@Override
	public void setCompositedId(String compositedId) {};


	@Override
	public SourceTypeEnum getSourceType() {
		return SourceTypeEnum.UNKNOWN;
	}
}
