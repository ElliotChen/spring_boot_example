package com.sportradar.sdp.ctrl;

import com.sportradar.sdp.repo.CustomerDao;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sportradar.sdp.model.Customer;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer/*")
public class CustomerCtrl {

	private CustomerDao customerDao;

	@GetMapping("/")
	public String index() {
		return "customer/index";
	}

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Customer> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Customer> ds = null;//this.customerDao.findAll(input);
		return  ds;
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		model.addAttribute("customer", this.customerDao.findById(id));
		return "customer/pair";
	}

	@PostMapping("/savePair")
	public String savePair(Model model) {
		model.addAttribute("successFlash", "Success!");
		return "customer/index";
	}
}
