package com.sportradar.sdp.domain.dgt;

import com.sportradar.sdp.domain.common.BaseLeagueGroup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Slf4j
@Entity
@Table(name = "LeagueGroup")
public class LeagueGroup extends BaseLeagueGroup {
}
