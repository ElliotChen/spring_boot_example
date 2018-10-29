package tw.elliot.mem.dao.sdp;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.mem.domain.sdp.Sport;

@Repository
public interface SdpSportDao extends DataTablesRepository<Sport, Long> {

}
