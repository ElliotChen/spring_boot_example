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
@Table(name = "EventType_I18N")
@EqualsAndHashCode(of = {"eventTypeId", "languageCode"})
public class EventTypeLanguage implements Serializable {
	@Id
	private Integer eventTypeId;
	@Id
	private Long languageCode;
	private String eventTypeName;

	@LastModifiedDate
	private Date updatedTime;
}
