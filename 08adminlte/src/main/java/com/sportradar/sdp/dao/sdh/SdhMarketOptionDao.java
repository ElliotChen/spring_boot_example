package com.sportradar.sdp.dao.sdh;

import com.sportradar.sdp.domain.common.MarketOptionPK;
import com.sportradar.sdp.domain.sdh.Market;
import com.sportradar.sdp.domain.sdh.MarketOption;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface SdhMarketOptionDao extends DataTablesRepository<MarketOption, MarketOptionPK> {
}
