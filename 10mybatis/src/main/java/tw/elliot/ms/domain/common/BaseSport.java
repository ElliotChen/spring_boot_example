package tw.elliot.ms.domain.common;

import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class BaseSport extends BaseEntity {

	private Long sportId;

	private String sportName;

	private Integer priority;

	private Timestamp updatedTime;
}
