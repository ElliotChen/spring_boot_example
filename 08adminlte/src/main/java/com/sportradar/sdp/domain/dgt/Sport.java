package com.sportradar.sdp.domain.dgt;

import com.sportradar.sdp.domain.common.BaseSport;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "Sport")
@Data
@Slf4j
public class Sport extends BaseSport {

	private com.sportradar.sdp.domain.sdh.Sport sport;
}
