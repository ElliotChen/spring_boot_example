package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.Market;
import com.sportradar.sdh.domain.br.MarketOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrMarketOptionDao {
	MarketOption findById(@Param("marketId") Long marketId,
	                      @Param("marketTypeId") Integer marketTypeId,
	                      @Param("optionNum") Integer optionNum,
	                      @Param("optionType") String optionType);

	Integer countById(@Param("marketId") Long marketId,
	                  @Param("marketTypeId") Integer marketTypeId,
	                  @Param("optionNum") Integer optionNum,
	                  @Param("optionType") String optionType);

	Integer findNextOptionNum(@Param("marketId") Long marketId,
	                          @Param("marketTypeId") Integer marketTypeId,
	                          @Param("optionNum") Integer optionNum,
	                          @Param("optionType") String optionType);

	List<MarketOption> findAll();

	List<MarketOption> findByMarket(@Param("market") Market market);

	void updatePair(@Param("marketOption") MarketOption marketOption, @Param("marketOptionIdXRef") String marketOptionIdXRef);

}
