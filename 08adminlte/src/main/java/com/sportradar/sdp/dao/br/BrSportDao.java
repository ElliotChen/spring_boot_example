package com.sportradar.sdp.dao.br;

import com.sportradar.sdp.domain.br.Sport;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrSportDao extends CrudRepository<Sport, Long> {
}
