package tw.elliot.adm.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.elliot.adm.model.Customer;
import tw.elliot.adm.repo.CustomerDao;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer/*")
public class CustomerCtrl {
	@Autowired
	private CustomerDao customerDao;

	@RequestMapping("/")
	public String index() {
		return "customer/index";
	}

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Customer> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Customer> ds = this.customerDao.findAll(input);
		return  ds;
	}
}
