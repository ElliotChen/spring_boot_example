package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseSport;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
public class Sport extends BaseSport {

	private List<com.sportradar.sdh.domain.dgt.Sport> dgtSportXRefs = new ArrayList<>();

	private List<com.sportradar.sdh.domain.br.Sport> brSportXRefs = new ArrayList<>();

	private List<BaseSport> referSportXRefs = new ArrayList<>();

	public Sport(Long sportId) {
		this(sportId, "", "");
	}

	public Sport(Long sportId, String dgtSportIds, String brSportIds) {
		this.setSportId(sportId);

		String[] refSportIds = org.apache.commons.lang3.StringUtils.split(dgtSportIds, ',');
		dgtSportXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				if (StringUtils.isNumeric(id) && !"-1".equals(id)) {
					dgtSportXRefs.add(new com.sportradar.sdh.domain.dgt.Sport(Long.parseLong(id), sportId));
				}
			}
		}


		refSportIds = org.apache.commons.lang3.StringUtils.split(brSportIds, ',');
		brSportXRefs.clear();
		if (null != refSportIds) {
			for (String id : refSportIds) {
				if (StringUtils.isNumeric(id) && !"-1".equals(id)) {
					brSportXRefs.add(new com.sportradar.sdh.domain.br.Sport(Long.parseLong(id)));
				}
			}
		}

		referSportXRefs.clear();
		referSportXRefs.addAll(this.dgtSportXRefs);
		referSportXRefs.addAll(this.brSportXRefs);
	}

	/*
	@PostLoad
	public void splitSportIdXRefs() {
		String tmpSportIdXRefs = this.getSportIdXRefs();

		if (StringUtils.isEmpty(tmpSportIdXRefs)) {
			return;
		}

		if (!tmpSportIdXRefs.contains("|")) {
			return;
		}

		String[] refs = tmpSportIdXRefs.split("\\|");

		if (refs.length != 2) {
			log.warn("Incorrect Size for sportIdXRefs[{}], please check SDH Sport id[{}]", tmpSportIdXRefs, this.getSportId());
		}

		this.dgtSportIdXRefs = refs[0];
		this.brSportIdXRefs = refs[1];

	}


	public String getLangString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		//Make sure Fetch executing
		List<SportLanguage> langs = this.getLanguages();

		if (null != langs && !langs.isEmpty()) {
			Iterator<SportLanguage> it = langs.iterator();

			while(it.hasNext()) {
				SportLanguage sl = it.next();
				sb.append(sl.getLanguageCode());
				sb.append(":");
				sb.append(sl.getSportName());
				if (it.hasNext()) {
					sb.append(",");
				}
			}
		}

		sb.append("]");

		return sb.toString();
	}

	@Override
	public String getExpressId() {
		return this.getSportId().toString();
	}

	@Override
	public String getIdXRefs() {
		return this.getSportIdXRefs();
	}

	@Override
	public void setMergedIdXRefs(String mergedIdXRefs) {
		this.setSportIdXRefs(mergedIdXRefs);
	}
	*/
}
