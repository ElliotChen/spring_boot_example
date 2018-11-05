package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.Market;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrMarketDao {
	Market findById(@Param("marketId") Long marketId, @Param("marketTypeId") Integer marketTypeId);

	Integer countById(@Param("marketId") Long marketId, @Param("marketTypeId") Integer marketTypeId);

	Long findNextId();

	List<Market> findAll();

	List<Market> findByMarketTypeId(@Param("marketTypeId") Integer marketTypeId);
}
