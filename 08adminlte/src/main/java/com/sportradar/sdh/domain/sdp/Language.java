package com.sportradar.sdh.domain.sdp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Language {

	private Integer languageCode;

	private String languageName;
}
