package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseLeague;
import com.sportradar.sdp.domain.dgt.LeagueGroup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Data
@Entity
@Table(name = "League")
public class League extends BaseLeague {
	/*
	@Transient
	private String brLeagueIdXRefs;

	@Transient
	private String dgtLeagueIdXRefs;
	*/

	@Transient
	private List<com.sportradar.sdp.domain.dgt.League> dgtLeagueXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.League> brLeagueXRefs;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="leagueId", cascade = {CascadeType.PERSIST})
	private List<LeagueLanguage> languages;

	@Override
	public String getExpressId() {
		return this.getLeagueId().toString();
	}

	@Override
	public String getIdXRefs() {
		return this.getLeagueIdXRefs();
	}

	@Override
	public void setMergedIdXRefs(String mergedIdXRefs) {
		this.setLeagueIdXRefs(mergedIdXRefs);
	}


	public String getLangString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		//Make sure Fetch executing
		List<LeagueLanguage> langs = this.getLanguages();

		if (null != langs && !langs.isEmpty()) {
			Iterator<LeagueLanguage> it = langs.iterator();

			while(it.hasNext()) {
				LeagueLanguage sl = it.next();
				sb.append(sl.getLanguageCode());
				sb.append(":");
				sb.append(sl.getLeagueName());
				if (it.hasNext()) {
					sb.append(",");
				}
			}
		}

		sb.append("]");

		return sb.toString();
	}
}
