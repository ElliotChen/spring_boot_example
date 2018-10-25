package com.sportradar.sdp.domain.common;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseRegion extends BaseReferenceModel {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer regionNum;
	private String regionShortName;
	private String regionFullName;
	private String alias;

	@LastModifiedDate
	private Date updatedTime;

	private String regionNumXRefs;
}
