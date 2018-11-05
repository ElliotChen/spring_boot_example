package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.EventType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrEventTypeDao {
	List<EventType> findAll();
	EventType findById(@Param("eventTypeId") Integer eventTypeId);
	List<EventType> findAllForMarket(Long marketId);
}
