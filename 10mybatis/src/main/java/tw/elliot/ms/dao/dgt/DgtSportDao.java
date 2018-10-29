package tw.elliot.ms.dao.dgt;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tw.elliot.ms.domain.dgt.Sport;

import java.util.List;


@Mapper
public interface DgtSportDao {
	Sport findById(@Param("sportId") Long sportId);

	List<Sport> findAll();
}
