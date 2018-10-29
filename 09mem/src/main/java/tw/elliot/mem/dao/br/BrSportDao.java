package tw.elliot.mem.dao.br;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.mem.domain.br.Sport;

@Repository
public interface BrSportDao extends CrudRepository<Sport, Long> {
}
