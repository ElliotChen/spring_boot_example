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

	protected Integer regionNum;
	protected String regionShortName;
	protected String regionFullName;
	protected String alias;

	protected Date updatedTime;

}
