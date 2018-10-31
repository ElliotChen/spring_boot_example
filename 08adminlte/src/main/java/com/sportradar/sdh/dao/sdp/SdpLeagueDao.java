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

	Integer countById(@Param("leagueId") Long leagueId);

	Long findNextId();

	List<League> findAll();

	Page<League> findByPage();

	List<League> findAllWithLanguage();

	List<League> findByIdWithLanguage(@Param("leagueId") Long leagueId);

	List<League> findByIdWithAllLanguage(@Param("leagueId") Long leagueId);

	League findByIdAndLanguageCodeWithLanguage(@Param("leagueId") Long leagueId, @Param("languageCode") Integer languageCode);

	void insertI18N(League sport);

	void updateI18N(League sport);

	void insertData(League sport);

	void updateData(League sport);
}
