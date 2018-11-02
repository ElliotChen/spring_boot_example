package com.sportradar.sdh.dao.sdp.handler;

import com.sportradar.sdh.domain.common.BaseSport;
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
public class SportsHandler <T extends BaseSport> extends BaseTypeHandler<List<BaseSport>> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseSport> baseSports, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseSport> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return buildSports(resultSet.getString(s));
	}

	@Override
	public List<BaseSport> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return buildSports(resultSet.getString(i));
	}

	@Override
	public List<BaseSport> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return buildSports(callableStatement.getString(i));
	}

	/**
	 *
	 * @param compositedIds sportId | dgtSportId | brSportId, like 53|53|109
	 * @return
	 */
	private List<BaseSport> buildSports(String compositedIds) {
		log.debug("buildSports for [{}]", compositedIds);
		List<BaseSport> sports = new ArrayList<>();

		com.sportradar.sdh.domain.sdp.Sport sdpSport = new com.sportradar.sdh.domain.sdp.Sport();
		com.sportradar.sdh.domain.dgt.Sport dgtSport = new com.sportradar.sdh.domain.dgt.Sport();
		com.sportradar.sdh.domain.br.Sport brSport = new com.sportradar.sdh.domain.br.Sport();

		dgtSport.setSportRef(sdpSport);
		brSport.setSportRef(sdpSport);

		sports.add(dgtSport);
		sports.add(brSport);

		if (StringUtils.isEmpty(compositedIds) || StringUtils.contains(compositedIds, "\\|")) {
			return sports;
		}

		String[] ids = compositedIds.split("\\|");

		log.debug("init sport[{}] with dgt[{}], br[{}]", ids[0], ids[1], ids[2]);
		sdpSport.setCompositedId(ids[0]);
		dgtSport.setCompositedId(ids[1]);
		brSport.setCompositedId(ids[2]);


		return sports;
	}
}
