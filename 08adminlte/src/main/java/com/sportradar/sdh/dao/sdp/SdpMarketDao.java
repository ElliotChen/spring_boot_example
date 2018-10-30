package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Market;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpMarketDao {
	Market findById(@Param("marketId") Long marketId);

	List<Market> findAll();

	Page<Market> findByPage();

	List<Market> findAllWithLanguage();

	List<Market> findByIdWithLanguage(@Param("marketId") Long marketId);

	List<Market> findByIdWithAllLanguage(@Param("marketId") Long marketId);

	Market findByIdAndLanguageCodeWithLanguage(@Param("marketId") Long marketId, @Param("languageCode") Integer languageCode);

	void insertI18N(Market market);

	void updateI18N(Market market);
}
