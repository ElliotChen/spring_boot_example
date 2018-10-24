package com.sportradar.sdp.domain.br;

import com.sportradar.sdp.domain.common.BaseSport;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Sport")
@Data
public class Sport extends BaseSport {
	private com.sportradar.sdp.domain.sdh.Sport sport;
}
