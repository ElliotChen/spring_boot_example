package com.sportradar.sdp.domain.dgt;

import com.sportradar.sdp.domain.common.BaseMatchInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Slf4j
@Data
@Entity
@Table(name = "MatchInfo")
public class MatchInfo extends BaseMatchInfo {
}
