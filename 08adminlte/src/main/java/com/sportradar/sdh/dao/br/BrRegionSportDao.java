package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.RegionSport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrRegionSportDao {
	void updatePair(@Param("regionSport") RegionSport regionSport, @Param("regionSportXRef") String regionSportXRef);
}
