package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.MarketOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtMarketOptionDao {
	MarketOption findById(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	Integer countById(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	Integer findNextOptionNum(@Param("marketId") Long marketId);

	List<MarketOption> findAll();

	List<MarketOption> findByMarketId(@Param("marketId") Long marketId);

	void updatePair(@Param("marketOption") MarketOption marketOption, @Param("marketOptionIdXRef") String marketOptionIdXRef);

}
