package com.sportradar.sdp.dao.sdh;

import com.sportradar.sdp.domain.sdh.League;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface SdhLeagueDao extends DataTablesRepository<League, Long> {
}
