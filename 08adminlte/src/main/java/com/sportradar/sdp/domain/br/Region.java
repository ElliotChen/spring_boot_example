package com.sportradar.sdp.domain.br;

import com.sportradar.sdp.domain.common.BaseRegion;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Slf4j
@Entity
@Table(name = "Region")
public class Region extends BaseRegion {
	@Override
	public String getExpressId() {
		return this.getRegionNum().toString();
	}

	@Override
	public String getIdXRefs() {
		return this.getRegionNumXRefs();
	}

	@Override
	public void setMergedIdXRefs(String mergedIdXRefs) {

	}
}
