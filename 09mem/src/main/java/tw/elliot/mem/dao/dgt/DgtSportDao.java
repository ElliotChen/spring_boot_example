package tw.elliot.mem.dao.dgt;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.mem.domain.dgt.Sport;

@Repository
public interface DgtSportDao extends CrudRepository<Sport, Long> {
}
