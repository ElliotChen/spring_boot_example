package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.RegionSport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DgtRegionSportDao {
	void updatePair(@Param("regionSport") RegionSport regionSport, @Param("regionSportXRef") String regionSportXRef);
}
