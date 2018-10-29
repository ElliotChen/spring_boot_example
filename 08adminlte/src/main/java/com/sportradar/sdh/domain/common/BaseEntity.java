package com.sportradar.sdh.domain.common;

public class BaseEntity {
	protected Boolean isPreloaded = new Boolean(false);

	protected Language language = new Language(1,"English");

	protected Partner partner = new Partner(1,"QL");
}
