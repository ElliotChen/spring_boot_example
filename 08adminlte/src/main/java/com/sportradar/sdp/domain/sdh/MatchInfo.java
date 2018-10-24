package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseMatchInfo;
import com.sportradar.sdp.domain.dgt.League;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Data
@Entity
@Table(name = "MatchInfo")
public class MatchInfo extends BaseMatchInfo {
	@Transient
	private String brMatchInfoIdXRefs;

	@Transient
	private String dgtMatchInfoIdXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.dgt.MatchInfo> dgtLMatchInfoXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.MatchInfo> brMatchInfoXRefs;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="matchId", cascade = {CascadeType.PERSIST})
	private List<MatchInfoLanguage> languages;
}
