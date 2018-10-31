package com.sportradar.sdh.dao.sdp;

import com.sportradar.sdh.domain.sdp.EventType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpEventTypeDao {
	EventType findById(@Param("eventTypeId") Integer eventTypeId);


	List<EventType> findAll();
}
