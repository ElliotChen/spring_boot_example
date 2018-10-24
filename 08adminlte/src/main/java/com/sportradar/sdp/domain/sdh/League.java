package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseLeague;
import com.sportradar.sdp.domain.dgt.LeagueGroup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Data
@Entity
@Table(name = "League")
public class League extends BaseLeague {
	@Transient
	private String brLeagueIdXRefs;

	@Transient
	private String dgtLeagueIdXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.dgt.League> dgtLeagueXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.League> brLeagueXRefs;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="leagueId", cascade = {CascadeType.PERSIST})
	private List<LeagueLanguage> languages;
}
