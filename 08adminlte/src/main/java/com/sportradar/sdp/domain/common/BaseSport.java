package com.sportradar.sdp.domain.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseSport {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long sportId;

	private String sportName;

	@CreatedDate
	private Date createdTime;

	@LastModifiedDate
	private Date updatedTime;

	private String sportIdXRefs;
}
