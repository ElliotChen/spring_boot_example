package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.common.MarketOptionPK;
import com.sportradar.sdh.domain.sdp.Market;
import com.sportradar.sdh.domain.sdp.MarketOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.List;

@Mapper
public interface SdpMarketOptionDao {
	MarketOption findById(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	List<MarketOption> findAll();

	Page<MarketOption> findByPage();
}
