package com.sportradar.sdh.dao.sdp.handler;

import com.sportradar.sdh.domain.common.BaseRegion;
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
public class RegionsHandler<T extends BaseRegion> extends BaseTypeHandler<List<BaseRegion>> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseRegion> baseRegions, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseRegion> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return this.buildRegions(resultSet.getString(s));
	}

	@Override
	public List<BaseRegion> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return this.buildRegions(resultSet.getString(i));
	}

	@Override
	public List<BaseRegion> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return this.buildRegions(callableStatement.getString(i));
	}

	/**
	 *
	 * @param compositedIds sportId | dgtSportId | brSportId, like 53|53|109
	 * @return
	 */
	private List<BaseRegion> buildRegions(String compositedIds) {
		log.debug("buildRegions for [{}]", compositedIds);
		List<BaseRegion> regions = new ArrayList<>();

		com.sportradar.sdh.domain.sdp.Region sdpRegion = new com.sportradar.sdh.domain.sdp.Region();
		com.sportradar.sdh.domain.dgt.Region dgtRegion = new com.sportradar.sdh.domain.dgt.Region();
		com.sportradar.sdh.domain.br.Region brRegion = new com.sportradar.sdh.domain.br.Region();

		dgtRegion.setRegionRef(sdpRegion);
		brRegion.setRegionRef(sdpRegion);

		regions.add(dgtRegion);
		regions.add(brRegion);

		if (StringUtils.isEmpty(compositedIds) || StringUtils.contains(compositedIds, "\\|")) {
			return regions;
		}

		String[] ids = compositedIds.split("\\|");

		log.debug("init region[{}] with dgt[{}], br[{}]", ids[0], ids[1], ids[2]);
		sdpRegion.setCompositedId(ids[0]);
		dgtRegion.setCompositedId(ids[1]);
		brRegion.setCompositedId(ids[2]);


		return regions;
	}
}
