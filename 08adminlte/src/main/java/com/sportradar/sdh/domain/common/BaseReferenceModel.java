package com.sportradar.sdh.domain.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class BaseReferenceModel {

	protected String brIdXRefs;

	protected String dgtIdXRefs;

	public abstract String getExpressId();

	public abstract String getIdXRefs();
	public abstract void setMergedIdXRefs(String mergedIdXRefs);

	/*
	@PostLoad
	public void splitIdXRefs() {
		String tmpSportIdXRefs = this.getIdXRefs();

		if (StringUtils.isEmpty(tmpSportIdXRefs)) {
			return;
		}

		if (!tmpSportIdXRefs.contains("|")) {
			return;
		}

		String[] refs = tmpSportIdXRefs.split("\\|");

		if (refs.length != 2) {
			log.warn("Incorrect Size for IdXRefs[{}], please check id[{}]", tmpSportIdXRefs, this.getExpressId());
		}

		this.brIdXRefs = refs[0];
		this.dgtIdXRefs = refs[1];
	}

	@PreUpdate
	public void mergeIdXRefs() {
		String tmpDgtIds = StringUtils.isEmpty(this.dgtIdXRefs)?"-1":this.dgtIdXRefs;
		String tmpBrIds = StringUtils.isEmpty(this.brIdXRefs)?"-1":this.brIdXRefs;


		this.setMergedIdXRefs(tmpDgtIds+"|"+tmpBrIds);
	}
	*/
}
