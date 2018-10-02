package tw.elliot.data.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tw.elliot.data.model.Customer;

@Repository
public interface PageCustomerRepo extends PagingAndSortingRepository<Customer, Long> {

	Page<Customer> findByAgeGreaterThan(Integer age, Pageable pageable);

	Page<Customer> findAll(Example<Customer> customer, Pageable pageable);
}
