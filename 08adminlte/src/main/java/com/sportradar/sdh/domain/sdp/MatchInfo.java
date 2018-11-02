package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseMatchInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class MatchInfo extends BaseMatchInfo {
	private String brMatchInfoIdXRefs;

	private String dgtMatchInfoIdXRefs;

	private List<com.sportradar.sdh.domain.dgt.MatchInfo> dgtLMatchInfoXRefs;

	private List<com.sportradar.sdh.domain.br.MatchInfo> brMatchInfoXRefs;

}
