package com.sportradar.sdh.domain.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
public abstract class BaseSport extends BaseEntity {

	protected Long sportId;
	protected String sportName;
	protected Integer priority;

	protected Date createdTime;
	protected Date updatedTime;

}
