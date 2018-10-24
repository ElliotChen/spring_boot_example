package com.sportradar.sdp.domain.sdh;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "BetMultiplicity")
public class BetMultiplicity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "ButMultiCode")
	private Integer betMultiCode;

	private String betMultiName;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="betMultiCode", cascade = {CascadeType.PERSIST})
	private List<BetMultiplicityLanguage> languages;
}
