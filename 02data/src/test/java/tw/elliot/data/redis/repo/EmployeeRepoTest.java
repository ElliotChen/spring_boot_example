package tw.elliot.data.redis.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.elliot.data.redis.model.Employee;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepoTest {

	@Autowired
	private EmployeeRepo repo;

	@Ignore
	@Test
	public void testCreate() {
		Assert.assertNotNull(repo);
		Employee employee = new Employee();
		employee.setId(123L);
		employee.setFirstname("A");
		employee.setLastname("B");

		repo.save(employee);
	}

	@Test
	public void testFindByFirstname() {
		List<Employee> employees = repo.findByLastname("B");
		Assert.assertEquals(1, employees.size());

		Employee employee = employees.get(0);

		log.info("Find Employee Firstname:[{}]", employee.getFirstname());
	}
}