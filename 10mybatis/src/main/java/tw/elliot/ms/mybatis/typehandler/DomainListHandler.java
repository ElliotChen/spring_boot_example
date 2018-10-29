package tw.elliot.ms.mybatis.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import tw.elliot.ms.domain.common.UpdateIdable;
import tw.elliot.ms.domain.dgt.Sport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DomainListHandler<T extends UpdateIdable> extends BaseTypeHandler<List<T>> {

	private Class<?> classOfT;

	public DomainListHandler() {
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
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<T> sports, JdbcType jdbcType) throws SQLException {
	}

	@Override
	public List<T> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return buildSportList(resultSet.getString(s));
	}

	@Override
	public List<T> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return buildSportList(resultSet.getString(i));
	}

	@Override
	public List<T> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return buildSportList(callableStatement.getString(i));
	}


	private List<T> buildSportList(String list) {
		List<T> result = new ArrayList<>();

		if(StringUtils.isEmpty(list)) {
			return result;
		}

		String[] ids = list.split(",");

		for (String id : ids) {
			T ui = (T) this.getInstance();
			if (null != ui) {
				ui.updateId(id);
				result.add(ui);
			} else {
				log.error("Can't initialize Class[{}] for Mybatis Handler, " +
						"Please check it's default constructor", classOfT.getName());
			}
		}

		return result;
	}

}
