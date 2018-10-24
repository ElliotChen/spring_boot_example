package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BetMultiplicity_I18N")
@Data
@EqualsAndHashCode(of = {"betMultiCode", "languageCode"},callSuper = false)
public class BetMultiplicityLanguage implements Serializable {
	@Id
	@Column(name = "ButMultiCode")
	private Long betMultiCode;
	@Id
	private Integer languageCode;

	@Column(name = "butMultiName")
	private String betMultiName;
}
