package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.Market;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtMarketDao {
	Market findById(@Param("marketId") Long marketId);

	Integer countById(@Param("marketId") Long marketId);

	Long findNextId();

	List<Market> findAll();
}
