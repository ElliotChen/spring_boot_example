package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Competitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpCompetitorDao {
    Competitor findById(@Param("competitorId") Long competitorId);

	Integer countById(@Param("competitorId") Long competitorId);

	Long findNextId();

	List<Competitor> findAll();

	Page<Competitor> findByPage();

	List<Competitor> findAllWithLanguage();

	List<Competitor> findByIdWithLanguage(@Param("competitorId") Long competitorId);

	List<Competitor> findByIdWithAllLanguage(@Param("competitorId") Long competitorId);

    Competitor findByIdAndLanguageCodeWithLanguage(@Param("competitorId") Long competitorId, @Param("languageCode") Integer languageCode);

	void insertI18N(Competitor competitor);

	void updateI18N(Competitor competitor);

	void insertData(Competitor competitor);

	void updateData(Competitor competitor);

	void updatePair(@Param("competitor") Competitor competitor, @Param("competitorXRefs") String competitorXRefs);
}
