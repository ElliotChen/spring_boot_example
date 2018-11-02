package com.sportradar.sdh.domain.common;

import lombok.Data;
import java.util.Date;

@Data
public abstract class BaseRegion extends BaseEntity {

	protected Integer regionNum;
	protected String regionShortName;
	protected String regionFullName;
	protected String alias;

	protected Date createdTime;
	protected Date updatedTime;

}
