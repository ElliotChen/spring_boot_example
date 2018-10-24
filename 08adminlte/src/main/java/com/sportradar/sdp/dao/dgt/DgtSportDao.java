package com.sportradar.sdp.dao.dgt;

import com.sportradar.sdp.domain.dgt.Sport;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface DgtSportDao extends DataTablesRepository<Sport, Long> {
}
