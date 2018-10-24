package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MatchStatus")
@Data
public class MatchStatus {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer statusCode;

	private String statusName;

	private String statusDesc;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="statusCode", cascade = {CascadeType.PERSIST})
	private List<MatchStatusLanguage> languages;
}
