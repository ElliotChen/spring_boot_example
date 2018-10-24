package com.sportradar.sdp.domain.sdh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Language")
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Language {

	@Id
	private Integer languageCode;

	private String languageName;
}
