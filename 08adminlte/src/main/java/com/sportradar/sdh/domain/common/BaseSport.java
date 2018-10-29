package com.sportradar.sdh.domain.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
public abstract class BaseSport extends BaseEntity {

	private Long sportId;
	private String sportName;
	private Integer priority;

	private Date createdTime;
	private Date updatedTime;

}
