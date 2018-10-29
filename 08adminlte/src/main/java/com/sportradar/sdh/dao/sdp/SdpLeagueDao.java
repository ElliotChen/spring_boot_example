package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.League;
import com.sportradar.sdh.domain.sdp.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.List;

@Mapper
public interface SdpLeagueDao {
	League findById(@Param("leagueId") Long leagueId);

	List<League> findAll();

	Page<League> findByPage();
}
