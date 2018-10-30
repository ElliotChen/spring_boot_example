package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpRegionDao {
	Region findById(@Param("regionNum") Integer regionNum);

	List<Region> findAll();

	Page<Region> findByPage();

	List<Region> findAllWithLanguage();

	List<Region> findByIdWithLanguage(@Param("regionNum") Integer regionNum);

	List<Region> findByIdWithAllLanguage(@Param("regionNum") Integer regionNum);

	Region findByIdAndLanguageCodeWithLanguage(@Param("regionNum") Integer regionNum, @Param("languageCode") Integer languageCode);

	void insertI18N(Region sport);

	void updateI18N(Region sport);
}
