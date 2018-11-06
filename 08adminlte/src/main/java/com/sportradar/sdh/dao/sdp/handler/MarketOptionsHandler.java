package com.sportradar.sdh.dao.sdp.handler;

import com.sportradar.sdh.domain.common.BaseMarketOption;
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
public class MarketOptionsHandler<T extends BaseMarketOption> extends BaseTypeHandler<List<BaseMarketOption>> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseMarketOption> baseMarketOptions, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseMarketOption> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return this.buildMarketOptions(resultSet.getString(s));
	}

	@Override
	public List<BaseMarketOption> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return this.buildMarketOptions(resultSet.getString(i));
	}

	@Override
	public List<BaseMarketOption> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return this.buildMarketOptions(callableStatement.getString(i));
	}

	/**
	 *
	 * @param compositedIds sportId | dgtSportId | brSportId, like 53|53|109
	 * @return
	 */
	private List<BaseMarketOption> buildMarketOptions(String compositedIds) {
		log.debug("build MarketOptions for [{}]", compositedIds);
		List<BaseMarketOption> marketOptions = new ArrayList<>();

		com.sportradar.sdh.domain.sdp.MarketOption sdpMarketOption = new com.sportradar.sdh.domain.sdp.MarketOption();
		com.sportradar.sdh.domain.dgt.MarketOption dgtMarketOption = new com.sportradar.sdh.domain.dgt.MarketOption();
		com.sportradar.sdh.domain.br.MarketOption brMarketOption = new com.sportradar.sdh.domain.br.MarketOption();

		dgtMarketOption.setMarketOptionRef(sdpMarketOption);
		brMarketOption.setMarketOptionRef(sdpMarketOption);

		marketOptions.add(dgtMarketOption);
		marketOptions.add(brMarketOption);

		if (StringUtils.isEmpty(compositedIds) || StringUtils.contains(compositedIds, "\\|")) {
			return marketOptions;
		}

		String[] ids = compositedIds.split("\\|");

		log.debug("init MarketOption[{}] with dgt[{}], br[{}]", ids[0], ids[1], ids[2]);
		sdpMarketOption.setCompositedId(ids[0]);
		dgtMarketOption.setCompositedId(ids[1]);
		brMarketOption.setCompositedId(ids[2]);


		return marketOptions;
	}
}
