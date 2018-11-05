package com.sportradar.sdh.dao.sdp.handler;

import com.sportradar.sdh.domain.common.BaseEntity;
import com.sportradar.sdh.domain.common.BaseMarket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MarketsHandler<T extends BaseMarket> extends BaseTypeHandler<List<BaseEntity>> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseEntity> baseMarkets, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseEntity> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return this.buildMarkets(resultSet.getString(s));
	}

	@Override
	public List<BaseEntity> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return this.buildMarkets(resultSet.getString(i));
	}

	@Override
	public List<BaseEntity> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return this.buildMarkets(callableStatement.getString(i));
	}

	/**
	 *
	 * @param compositedIds sportId | dgtSportId | brSportId, like 53|53|109
	 * @return
	 */
	private List<BaseEntity> buildMarkets(String compositedIds) {
		log.info("buildMarket for [{}]", compositedIds);
		List<BaseEntity> markets = new ArrayList<>();

		com.sportradar.sdh.domain.sdp.Market sdpMarket = new com.sportradar.sdh.domain.sdp.Market();
		com.sportradar.sdh.domain.dgt.SportMarket dgtSportMarket = new com.sportradar.sdh.domain.dgt.SportMarket();
		com.sportradar.sdh.domain.br.Market brMarket = new com.sportradar.sdh.domain.br.Market();

		dgtSportMarket.setMarketRef(sdpMarket);
		brMarket.setMarketRef(sdpMarket);

		markets.add(dgtSportMarket);
		markets.add(brMarket);

		if (StringUtils.isEmpty(compositedIds) || StringUtils.contains(compositedIds, "\\|")) {
			return markets;
		}

		String[] ids = compositedIds.split("\\|");

		log.info("init Market[{}] with dgt[{}], br[{}]", ids[0], ids[1], ids[2]);
		sdpMarket.setCompositedId(ids[0]);
		dgtSportMarket.setCompositedId(ids[1]);
		brMarket.setCompositedId(ids[2]);


		return markets;
	}
}
