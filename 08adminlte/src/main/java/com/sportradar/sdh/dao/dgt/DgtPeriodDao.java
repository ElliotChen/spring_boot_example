package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.Period;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtPeriodDao {
	Period findById(@Param("sportId") Long sportId, @Param("eventPartId") Integer eventPartId,
	                @Param("periodNum") Integer periodNum);

	List<Period> findAll();

	List<Period> findAllForSportMarket(@Param("sportId") Long sportId, @Param("eventPartId") Integer eventPartId, @Param("periodNum") Integer periodNum);
}
