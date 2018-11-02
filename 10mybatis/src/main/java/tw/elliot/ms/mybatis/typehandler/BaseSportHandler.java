package tw.elliot.ms.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseSportHandler<BaseSport> extends BaseTypeHandler<List<BaseSport>> {
	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<BaseSport> baseSports, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<BaseSport> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return null;
	}

	@Override
	public List<BaseSport> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return null;
	}

	@Override
	public List<BaseSport> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return null;
	}
}
