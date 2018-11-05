package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.League;
import com.sportradar.sdh.domain.dgt.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtLeagueDao {
	League findById(@Param("leagueId") Long leagueId);

	List<League> findAll();

	void updatePair(@Param("league") League league, @Param("leagueIdXRef") String leagueIdXRef);
}
