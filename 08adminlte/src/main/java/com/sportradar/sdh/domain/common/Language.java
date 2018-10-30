package com.sportradar.sdh.domain.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Language {

	protected Integer languageCode;

	protected String languageName;

	public Language(Integer languageCode, String languageName) {
		this.languageCode = languageCode;
		this.languageName = languageName;
	}
}
