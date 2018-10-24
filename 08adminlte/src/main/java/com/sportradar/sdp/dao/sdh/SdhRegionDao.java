package com.sportradar.sdp.dao.sdh;

import com.sportradar.sdp.domain.sdh.Region;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface SdhRegionDao extends DataTablesRepository<Region, Integer> {
}
