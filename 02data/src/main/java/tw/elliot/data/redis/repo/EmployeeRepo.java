package tw.elliot.data.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.data.redis.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	List<Employee> findByLastname(String lastname);
}
