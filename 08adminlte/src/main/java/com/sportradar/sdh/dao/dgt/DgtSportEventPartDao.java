package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.SportEventPart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DgtSportEventPartDao {
	SportEventPart findById(@Param("sportId") Long sportId, @Param("eventPartId") Integer eventPartId);

	List<SportEventPart> findAll();

	List<SportEventPart> findAllForSportMarket();
	List<SportEventPart> findAllForSportMarket(@Param("sportId") Long sportId);
}
