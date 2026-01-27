package pv.com.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pv.com.dto.OrdersDTO;

import pv.com.service.CustomerService;

@RestController
@Validated
@RequestMapping("/CustomerOrders")
public class OrderController {
	
	@Autowired
	private CustomerService customerService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private Environment environment;
	@PostMapping("/orders/{Id}")
	public ResponseEntity<String> createOrders(@PathVariable("Id") Long customerId, @RequestBody @Valid OrdersDTO orderDTO) throws Exception
	{
		String str = customerService.createOrder(customerId, orderDTO);
		logger.info("the dto class : "+orderDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(environment.getProperty("API.ORDER_INSERT_SUCESS"));
	}
	
	@GetMapping("/orders/{Id}")
	public ResponseEntity<List<OrdersDTO>> getOrders(@PathVariable("Id") Long customerId) throws Exception
	{
		
		List<OrdersDTO> ordersDTOList = customerService.getOrders(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(ordersDTOList);
	}

}
