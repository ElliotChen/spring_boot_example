package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "LeagueGroup_I18N")
@EqualsAndHashCode(of = {"leagueGroupId", "languageCode"})
public class LeagueGroupLanguage implements Serializable {
	@Id
	private Integer languageCode;
	@Id
	private Long leagueGroupId;
	private String leagueGroupName;

	@LastModifiedDate
	private Date updatedTime;
}
