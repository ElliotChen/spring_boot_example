package tw.elliot.ms.dao.sdp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tw.elliot.ms.domain.sdp.Sport;

import java.util.List;

@Mapper
public interface SdpSportDao {
	Sport findById(@Param("sportId") Long sportId);

	List<Sport> findAll();
}
