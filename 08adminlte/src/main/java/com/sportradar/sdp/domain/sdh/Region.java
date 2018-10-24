package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseRegion;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@Entity
@Table(name = "Region")
public class Region extends BaseRegion {
	@Transient
	private String brRegionIdXRefs;

	@Transient
	private String dgtRegionIdXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.dgt.Region> dgtRegionXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.Region> brRegionXRefs;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="regionNum", cascade = {CascadeType.PERSIST})
	private List<RegionLanguage> languages;
}
