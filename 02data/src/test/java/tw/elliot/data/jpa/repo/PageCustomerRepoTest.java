package tw.elliot.data.jpa.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tw.elliot.data.jpa.model.Customer;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PageCustomerRepoTest {

	@Autowired
	private PageCustomerRepo repo;

	@Test
	public void testTotalSize() {
		Pageable pageable = PageRequest.of(0, 5);

		Page<Customer> page = repo.findAll(pageable);

		log.info("TotalPage [{}]", page);

		Assertions.assertEquals(10, page.getTotalElements());
	}

	@Test
	public void testAge() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Customer> page = repo.findByAgeGreaterThan(20, pageable);
		Assertions.assertEquals(10, page.getTotalElements());

		page = repo.findByAgeGreaterThan(50, pageable);
		Assertions.assertEquals(0, page.getTotalElements());
	}

	@Test
	public void testQBE() {
		Customer customer = new Customer();
		customer.setAge(30);

		Pageable pageable = PageRequest.of(0, 5);
		Page<Customer> page = repo.findAll(Example.of(customer), pageable);
		Assertions.assertEquals(8, page.getTotalElements());

	}
}