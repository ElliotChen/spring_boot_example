package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseLeagueGroup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Data
@Slf4j
public class LeagueGroup extends BaseLeagueGroup {
	private String brLeagueGroupIdXRefs;

	private String dgtLeagueGroupIdXRefs;

	private List<com.sportradar.sdh.domain.dgt.LeagueGroup> dgtLeagueGroupXRefs;

	private List<com.sportradar.sdh.domain.br.LeagueGroup> brLeagueGroupXRefs;

}
