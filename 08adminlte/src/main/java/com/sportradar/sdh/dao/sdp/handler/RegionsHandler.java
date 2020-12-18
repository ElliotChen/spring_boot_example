package com.sportradar.sdh.dao.sdp.handler;

import com.sportradar.sdh.domain.common.BaseRegionSport;
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
public class RegionsHandler<T extends BaseRegionSport> extends BaseTypeHandler<List<BaseRegionSport>> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseRegionSport> baseRegions, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseRegionSport> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return this.buildRegions(resultSet.getString(s));
	}

	@Override
	public List<BaseRegionSport> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return this.buildRegions(resultSet.getString(i));
	}

	@Override
	public List<BaseRegionSport> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return this.buildRegions(callableStatement.getString(i));
	}

	/**
	 *
	 * @param compositedIds sportId | dgtSportId | brSportId, like 53|53|109
	 * @return
	 */
	private List<BaseRegionSport> buildRegions(String compositedIds) {
		log.debug("buildRegions for [{}]", compositedIds);
		List<BaseRegionSport> regionSports = new ArrayList<>();

		com.sportradar.sdh.domain.sdp.Region sdpRegion = new com.sportradar.sdh.domain.sdp.Region();
		com.sportradar.sdh.domain.dgt.RegionSport dgtRegionSport = new com.sportradar.sdh.domain.dgt.RegionSport();
		com.sportradar.sdh.domain.br.RegionSport brRegionSport = new com.sportradar.sdh.domain.br.RegionSport();

		dgtRegionSport.setRegionRef(sdpRegion);
		brRegionSport.setRegionRef(sdpRegion);

		regionSports.add(dgtRegionSport);
		regionSports.add(brRegionSport);

		if (StringUtils.isEmpty(compositedIds) || StringUtils.contains(compositedIds, "\\|")) {
			return regionSports;
		}

		String[] ids = compositedIds.split("\\|");

		log.debug("init region[{}] with dgt[{}], br[{}]", ids[0], ids[1], ids[2]);
		sdpRegion.setCompositedId(ids[0]);
		dgtRegionSport.setCompositedId(ids[1]);
		brRegionSport.setCompositedId(ids[2]);


		return regionSports;
	}
}
