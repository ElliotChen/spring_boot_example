package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseCompetitor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Competitor")
@Data
public class Competitor extends BaseCompetitor {
	@Transient
	private String brCompetitorIdXRefs;

	@Transient
	private String dgtCompetitorIdXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.dgt.Competitor> dgtCompetitorXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.Competitor> brCompetitorXRefs;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="competitorId", cascade = {CascadeType.PERSIST})
	private List<CompetitorLanguage> languages;
}
