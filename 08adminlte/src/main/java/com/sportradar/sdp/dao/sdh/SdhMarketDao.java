package com.sportradar.sdp.dao.sdh;

import com.sportradar.sdp.domain.sdh.Market;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface SdhMarketDao extends DataTablesRepository<Market, Long> {
}
