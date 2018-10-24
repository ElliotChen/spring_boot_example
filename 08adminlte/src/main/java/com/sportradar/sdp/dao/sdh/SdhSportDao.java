package com.sportradar.sdp.dao.sdh;

import com.sportradar.sdp.domain.sdh.Sport;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SdhSportDao extends DataTablesRepository<Sport, Long> {
}
