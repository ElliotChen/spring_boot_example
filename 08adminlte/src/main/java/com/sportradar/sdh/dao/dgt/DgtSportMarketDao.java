package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.SportMarket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DgtSportMarketDao {
	void updatePair(@Param("sportMarket") SportMarket sportMarket, @Param("sportMarketXRef") String sportMarketXRef);
}
