package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Sport_I18N")
@EqualsAndHashCode(of = {"sportId", "languageCode"})
public class SportLanguage implements Serializable {

	@Id
	private Long sportId;

	@Id
	private Integer languageCode;

	/*
	@ManyToOne
	@JoinColumn(name = "languageCode")
	private Language language;
	*/

	private String sportName;

	@LastModifiedDate
	private Date updatedTime;

	/*
	public Integer getLanguageCode() {
		return this.language != null ? this.language.getLanguageCode():null;
	}
	*/

}
