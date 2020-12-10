package tw.elliot.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.db.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
