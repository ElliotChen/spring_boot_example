package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.IdCompositable;
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
		super();

		this.sportId = sportId;

		this.initDgtXRefs(dgtSportIds);

		this.initBrXRefs(brSportIds);

		referSportXRefs.clear();
		referSportXRefs.addAll(this.dgtSportXRefs);
		referSportXRefs.addAll(this.brSportXRefs);
	}

	public void initDgtXRefs(String dgtXRefIds) {
		String[] xrefIds = StringUtils.split(dgtXRefIds, ',');
		dgtSportXRefs.clear();
		if (null != xrefIds) {
			for (String id : xrefIds) {
				if (StringUtils.isNumeric(id) && !"-1".equals(id)) {
					dgtSportXRefs.add(new com.sportradar.sdh.domain.dgt.Sport(Long.parseLong(id), sportId));
				}
			}
		}
	}

	public void initBrXRefs(String brXRefIds) {
		String[] xrefIds = StringUtils.split(brXRefIds, ',');
		brSportXRefs.clear();
		if (null != xrefIds) {
			for (String id : xrefIds) {
				if (StringUtils.isNumeric(id) && !"-1".equals(id)) {
					brSportXRefs.add(new com.sportradar.sdh.domain.br.Sport(Long.parseLong(id), sportId));
				}
			}
		}
	}

	@Override
	public String getCompositedId() {
		return String.valueOf(this.sportId);
	}

	public String getDgtIdXRefs() {
		return IdCompositable.joinCompositedId(this.dgtSportXRefs);
	}

	public String getBrIdXRefs() {
		return IdCompositable.joinCompositedId(this.brSportXRefs);
	}

}
