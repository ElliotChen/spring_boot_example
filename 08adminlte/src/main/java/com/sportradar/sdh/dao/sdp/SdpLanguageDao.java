package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.common.Language;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SdpLanguageDao {
	Language findById(@Param("languageCode") Integer languageCode);

	List<Language> findAll();

	Page<Language> findByPage();
}
