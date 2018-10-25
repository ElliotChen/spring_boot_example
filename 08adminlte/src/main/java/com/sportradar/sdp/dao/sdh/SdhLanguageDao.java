package com.sportradar.sdp.dao.sdh;

import com.sportradar.sdp.domain.sdh.Language;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SdhLanguageDao extends CrudRepository<Language, Integer> {
}
