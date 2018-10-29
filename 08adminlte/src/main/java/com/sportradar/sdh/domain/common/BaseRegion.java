package com.sportradar.sdh.domain.common;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
public abstract class BaseRegion extends BaseEntity {

	private Integer regionNum;
	private String regionShortName;
	private String regionFullName;
	private String alias;

	private Date updatedTime;

}
