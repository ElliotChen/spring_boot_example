package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private String sportName;

	@LastModifiedDate
	private Date updatedTime;
}
