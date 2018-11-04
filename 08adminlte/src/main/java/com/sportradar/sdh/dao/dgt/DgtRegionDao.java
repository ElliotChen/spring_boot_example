package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtRegionDao {
	Region findById(@Param("regionNum") Integer regionNum);

	List<Region> findAll();

	void savePair(@Param("region") Region region, @Param("regionNumXRef") String regionNumXRef);

	List<Region> findBySportId(@Param("sportId") Long sportId);
	List<Region> findAllForSport();
}
