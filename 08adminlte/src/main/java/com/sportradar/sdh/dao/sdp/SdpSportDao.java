package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpSportDao {
	Sport findById(@Param("sportId") Long sportId);

	Integer countById(@Param("sportId") Long sportId);

	Long findNextId();

	List<Sport> findAll();

	Page<Sport> findByPage();

	List<Sport> findAllWithLanguage();

	List<Sport> findByIdWithLanguage(@Param("sportId") Long sportId);

	List<Sport> findByIdWithAllLanguage(@Param("sportId") Long sportId);

	Sport findByIdAndLanguageCodeWithLanguage(@Param("sportId") Long sportId, @Param("languageCode") Integer languageCode);

	void insertI18N(Sport sport);

	void updateI18N(Sport sport);

	void insertData(Sport sport);

	void updateData(Sport sport);

	void updatePair(@Param("sportId") Long sportId, @Param("sportIdXRefs") String sportIdXRefs);
}
