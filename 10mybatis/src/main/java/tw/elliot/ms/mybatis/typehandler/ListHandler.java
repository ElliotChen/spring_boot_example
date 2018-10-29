package tw.elliot.ms.mybatis.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;
import tw.elliot.ms.domain.common.UpdateIdable;

import java.lang.reflect.ParameterizedType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListHandler extends BaseTypeHandler<List<? extends UpdateIdable>> {
	private Class<?> classOfT;

	public ListHandler() {
		this.classOfT = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		System.out.println(classOfT);
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
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<? extends UpdateIdable> updateIdables, JdbcType jdbcType) throws SQLException {

	}

	@Override
	public List<UpdateIdable> getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return buildSportList(resultSet.getString(s));
	}

	@Override
	public List<UpdateIdable> getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return buildSportList(resultSet.getString(i));
	}

	@Override
	public List<UpdateIdable> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return buildSportList(callableStatement.getString(i));
	}

	private List<UpdateIdable> buildSportList(String list) {
		List<UpdateIdable> result = new ArrayList<>();

		if(StringUtils.isEmpty(list)) {
			return result;
		}

		String[] ids = list.split(",");

		for (String id : ids) {
			UpdateIdable ui = (UpdateIdable) this.getInstance();
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
