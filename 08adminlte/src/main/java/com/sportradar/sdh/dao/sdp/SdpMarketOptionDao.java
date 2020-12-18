package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.MarketOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpMarketOptionDao {
	MarketOption findById(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	Integer countById(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	Integer findNextOptionNum(@Param("marketId") Long marketId);

	List<MarketOption> findAll();

	Page<MarketOption> findByPage();

	List<MarketOption> findAllWithLanguage();

	List<MarketOption> findByIdWithLanguage(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	List<MarketOption> findByIdWithAllLanguage(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum);

	MarketOption findByIdAndLanguageCodeWithLanguage(@Param("marketId") Long marketId, @Param("optionNum") Integer optionNum, @Param("languageCode") Integer languageCode);

	void insertI18N(MarketOption marketOption);

	void updateI18N(MarketOption marketOption);

	void insertData(MarketOption marketOption);

	void updateData(MarketOption marketOption);

	void updatePair(@Param("marketOption") MarketOption marketOption, @Param("marketOptionIdXRefs") String marketOptionIdXRefs);
}
