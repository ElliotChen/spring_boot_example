package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseReferenceModel;
import com.sportradar.sdp.domain.common.BaseSport;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Data
@Slf4j
@Entity
@Table(name = "Sport")
public class Sport extends BaseSport {

	/*
	@Transient
	private String brSportIdXRefs;

	@Transient
	private String dgtSportIdXRefs;

	*/
	@Transient
	private List<com.sportradar.sdp.domain.dgt.Sport> dgtSportXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.Sport> brSportXRefs;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="sportId", cascade = {CascadeType.PERSIST})
	private List<SportLanguage> languages;

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
	*/

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
}
