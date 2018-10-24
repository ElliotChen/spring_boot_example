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
@Table(name = "Region_I18N")
@EqualsAndHashCode(of = {"regionNum", "languageCode"})
public class RegionLanguage implements Serializable {

	@Id
	private Integer languageCode;
	@Id
	private Integer regionNum;
	private String regionName;

	@LastModifiedDate
	private Date updatedTime;
}
