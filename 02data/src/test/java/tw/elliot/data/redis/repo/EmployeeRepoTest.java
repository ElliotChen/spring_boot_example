package tw.elliot.data.redis.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tw.elliot.data.redis.model.Employee;

import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeRepoTest {

	@Autowired
	private EmployeeRepo repo;

	@Test
	public void testCreate() {
		Assertions.assertNotNull(repo);
		Employee employee = new Employee();
		employee.setId(123L);
		employee.setFirstname("A");
		employee.setLastname("B");

		repo.save(employee);
	}

	@Test
	public void testFindByFirstname() {
		List<Employee> employees = repo.findByLastname("B");
		Assertions.assertEquals(1, employees.size());

		Employee employee = employees.get(0);

		log.info("Find Employee Firstname:[{}]", employee.getFirstname());
	}
}