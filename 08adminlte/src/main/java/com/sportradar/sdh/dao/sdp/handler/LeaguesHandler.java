package com.sportradar.sdh.dao.sdp.handler;

import com.sportradar.sdh.domain.common.BaseLeague;
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
public class LeaguesHandler<T extends BaseLeague> extends BaseTypeHandler<List<BaseLeague>> {

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseLeague> baseLeagues, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseLeague> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return buildSports(resultSet.getString(s));
	}

	@Override
	public List<BaseLeague> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return buildSports(resultSet.getString(i));
	}

	@Override
	public List<BaseLeague> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return buildSports(callableStatement.getString(i));
	}

	/**
	 *
	 * @param compositedIds sportId | dgtSportId | brSportId, like 53|53|109
	 * @return
	 */
	private List<BaseLeague> buildSports(String compositedIds) {
		log.debug("buildLeagues for [{}]", compositedIds);
		List<BaseLeague> leagues = new ArrayList<>();

		com.sportradar.sdh.domain.sdp.League sdpLeague = new com.sportradar.sdh.domain.sdp.League();
		com.sportradar.sdh.domain.dgt.League dgtLeague = new com.sportradar.sdh.domain.dgt.League();
		com.sportradar.sdh.domain.br.League brLeague = new com.sportradar.sdh.domain.br.League();

		dgtLeague.setLeagueRef(sdpLeague);
		brLeague.setLeagueRef(sdpLeague);

		leagues.add(dgtLeague);
		leagues.add(brLeague);

		if (StringUtils.isEmpty(compositedIds) || StringUtils.contains(compositedIds, "\\|")) {
			return leagues;
		}

		String[] ids = compositedIds.split("\\|");

		log.debug("init League[{}] with dgt[{}], br[{}]", ids[0], ids[1], ids[2]);
		sdpLeague.setCompositedId(ids[0]);
		dgtLeague.setCompositedId(ids[1]);
		brLeague.setCompositedId(ids[2]);


		return leagues;
	}
}
