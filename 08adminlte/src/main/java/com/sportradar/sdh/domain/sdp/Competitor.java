package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseCompetitor;
import lombok.Data;

import java.util.List;

@Data
public class Competitor extends BaseCompetitor {
	private String brCompetitorIdXRefs;

	private String dgtCompetitorIdXRefs;

	private List<com.sportradar.sdh.domain.dgt.Competitor> dgtCompetitorXRefs;

	private List<com.sportradar.sdh.domain.br.Competitor> brCompetitorXRefs;

}
