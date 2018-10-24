package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseLeagueGroup;
import com.sportradar.sdp.domain.dgt.Region;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Data
@Slf4j
@Entity
@Table(name = "LeagueGroup")
public class LeagueGroup extends BaseLeagueGroup {
	@Transient
	private String brLeagueGroupIdXRefs;

	@Transient
	private String dgtLeagueGroupIdXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.dgt.LeagueGroup> dgtLeagueGroupXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.LeagueGroup> brLeagueGroupXRefs;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="languageCode", cascade = {CascadeType.PERSIST})
	private List<LeagueGroupLanguage> languages;
}
