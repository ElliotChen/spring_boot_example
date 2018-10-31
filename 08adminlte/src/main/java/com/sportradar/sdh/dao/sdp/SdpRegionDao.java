package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpRegionDao {
	Region findById(@Param("regionNum") Integer regionNum);

	Integer countById(@Param("regionNum") Integer regionNum);

	Integer findNextId();

	List<Region> findAll();

	Page<Region> findByPage();

	List<Region> findAllWithLanguage();

	List<Region> findByIdWithLanguage(@Param("regionNum") Integer regionNum);

	List<Region> findByIdWithAllLanguage(@Param("regionNum") Integer regionNum);

	Region findByIdAndLanguageCodeWithLanguage(@Param("regionNum") Integer regionNum, @Param("languageCode") Integer languageCode);

	void insertI18N(Region region);

	void updateI18N(Region region);

	void insertData(Region region);

	void updateData(Region region);


	List<Region> findBySportId(Long sportId);
}
