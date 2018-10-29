package tw.elliot.ms.mybatis.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import tw.elliot.ms.domain.common.UpdateIdable;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DomainHandler<T extends UpdateIdable> extends BaseTypeHandler<T> {
	private Class<?> classOfT;

	public DomainHandler() {
		this.classOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Object getInstance() {
		Object instance = null;
		try {
			instance = classOfT.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return this.buildDomain(resultSet.getString(s));
	}

	@Override
	public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return this.buildDomain(resultSet.getString(i));
	}

	@Override
	public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return this.buildDomain(callableStatement.getString(i));
	}


	private T buildDomain(String id) {
		T ui = (T) this.getInstance();
		if (null != ui) {
			ui.updateId(id);
		} else {
			log.error("Can't initialize Class[{}] for Mybatis Handler, " +
					"Please check it's default constructor", classOfT.getName());
		}
		return ui;
	}
}
