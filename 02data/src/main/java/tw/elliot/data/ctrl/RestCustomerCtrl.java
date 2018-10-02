package tw.elliot.data.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.elliot.data.model.Customer;
import tw.elliot.data.repo.PageCustomerRepo;

@RestController
@RequestMapping("/api/customer/*")
public class RestCustomerCtrl {
	@Autowired
	private PageCustomerRepo repo;


	@GetMapping("/all")
	public Page<Customer> findAll(@PageableDefault(value = 0, size = 5) Pageable pageable) {
		return repo.findAll(pageable);
	}

	/**
	 * Query Example
	 * http://127.0.0.1:8080/api/customer/example?page=0&size=5&age=30
	 * @param customer
	 * @param pageable
	 * @return
	 */
	@GetMapping("/example")
	public Page<Customer> findAll(@ModelAttribute Customer customer, @PageableDefault(value = 0, size = 5) Pageable pageable) {
		return repo.findAll(Example.of(customer), pageable);
	}


}
