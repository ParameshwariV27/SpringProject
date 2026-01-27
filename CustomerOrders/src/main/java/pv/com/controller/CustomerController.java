package pv.com.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pv.com.dto.CustomerDTO;
import pv.com.exceptions.CustomerOrderExceptions;
import pv.com.service.CustomerService;

@RestController
@Validated
@RequestMapping("/Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private Environment environment;
	
	@PostMapping("/create")
	public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerOrderExceptions
	{
		String str = customerService.CreateCustomer(customerDTO);
		logger.info("Customer dto "+customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(environment.getProperty("API.INSERT_SUCESS"));
		
	}
	
	@GetMapping("/get/{Id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long Id) throws CustomerOrderExceptions
	{
		logger.info("Id in Controller: "+Id);
		CustomerDTO customerDTO = customerService.findCustomer(Id);
		logger.info("customerDTO in controller: "+customerDTO.toString());
		return ResponseEntity.ok(customerDTO);
		
	}
	
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long Id) throws CustomerOrderExceptions
	{
		customerService.deleteCustomer(Id);
		return ResponseEntity.status(HttpStatus.OK.value()).body(environment.getProperty("API.DELETE_SUCESS"));
	}
	@PutMapping("update/{Id}")
	public ResponseEntity<String> updateCustomer(@PathVariable Long Id, @RequestBody CustomerDTO customerDTO) throws CustomerOrderExceptions
	{
		customerService.updateCustomer(Id, customerDTO);
		return ResponseEntity.status(HttpStatus.OK.value()).body(environment.getProperty("API.UPDATE_SUCESS"));
	}
}
