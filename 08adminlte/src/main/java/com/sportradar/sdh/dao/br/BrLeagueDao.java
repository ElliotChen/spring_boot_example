package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.League;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrLeagueDao {
	League findById(@Param("leagueId") Long leagueId);

	List<League> findAll();

	List<League> findBySportId(@Param("sportId") Long sportId);

	List<League> findByExample(League league);
	void updatePair(@Param("league") League league, @Param("leagueIdXRef") String leagueIdXRef);
}
