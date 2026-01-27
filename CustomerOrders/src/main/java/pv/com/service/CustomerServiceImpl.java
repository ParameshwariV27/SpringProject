package pv.com.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pv.com.controller.CustomerController;
import pv.com.dto.CustomerDTO;
import pv.com.dto.OrdersDTO;
import pv.com.entity.Customer;
import pv.com.entity.OrderStatus;
import pv.com.entity.Orders;
import pv.com.entity.PaymentMode;
import pv.com.entity.PaymentStatus;
import pv.com.exceptions.CustomerOrderExceptions;
import pv.com.repository.CustomerRepository;
import pv.com.repository.OrderRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private Environment environment;
	
	@Override
	public String CreateCustomer(CustomerDTO customerDTO) throws CustomerOrderExceptions{
		// TODO Auto-generated method stub
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		customerRepository.save(customer);
		return "sucess";
	}
	@Override
	public CustomerDTO findCustomer(Long Id) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Customer> opt = customerRepository.findById(Id);
		Customer customer = opt.orElseThrow(()->new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));
		
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		System.out.println(customerDTO);
		return customerDTO;
	}
	@Override
	public String createOrder(Long customerId, OrdersDTO orderDTO) throws CustomerOrderExceptions {

	    Customer customer = customerRepository.findById(customerId)
	                            .orElseThrow(() -> new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));

	    Orders orders = modelMapper.map(orderDTO, Orders.class);
	    //Adding validations
	    orders.setOrderStatus(OrderStatus.CREATED);
	    if(orders.getTransactionId()!=null)
	    	orders.setPaymentStatus(PaymentStatus.SUCESS);
	    else
	    	orders.setPaymentStatus(PaymentStatus.PENDING);
	    orders.setOrderDate(LocalDateTime.now());
	    orders.setExpectedDelivery(LocalDateTime.now().plusDays(2).toLocalDate());
	    if(orders.getPaymentStatus().equals(PaymentStatus.PENDING))
	    	orders.setPaymentMode(PaymentMode.CASH);
	    BigDecimal quantity = BigDecimal.valueOf(orders.getQuantity());
	    BigDecimal price = BigDecimal.valueOf(1200);
	    BigDecimal amt = quantity.multiply(price);
	    orders.setTotalAmount(amt);
	    //end---------
	    orders.setCustomer(customer);  
	    customer.getOrders().add(orders);

	    orderRepository.save(orders); 

	    return "success";
	}

	@Override
	public List<OrdersDTO> getOrders(Long customerId) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Customer> opt= customerRepository.findById(customerId);
		Customer customer = opt.orElseThrow(()->new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));
		List<OrdersDTO> ordersDTOList = new ArrayList<>();
		for(Orders ol: customer.getOrders())
		{
			OrdersDTO dto = new OrdersDTO();
			dto = modelMapper.map(ol,OrdersDTO.class);
			ordersDTOList.add(dto);
		}
		return ordersDTOList;
	}
	
	
	@Scheduled(cron = "0 0 * * * ?")
	public void update()
	{
		//logger.info("CRON..!!!");
		List<Orders> orders = orderRepository.findAll();
		for(Orders o : orders)
		{
			if(o.getExpectedDelivery().equals(LocalDate.now()) && o.getPaymentStatus().equals("SUCESS"))
			{
				o.setOrderStatus(OrderStatus.DELIVERED);
				orderRepository.save(o);
			}
		}
	}
	@Override
	public void deleteCustomer(Long Id) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Customer> opt= customerRepository.findById(Id);
		Customer customer = opt.orElseThrow(()->new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));
		customerRepository.delete(customer);
	}
	@Override
	public String updateCustomer(Long Id, CustomerDTO customerDTO) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Customer> opt= customerRepository.findById(Id);
		Customer customer = opt.orElseThrow(()->new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));
		if(customerDTO.getFirstName()!="")
			customer.setFirstName(customerDTO.getFirstName());
		
		if(customerDTO.getLastName()!="")
			customer.setLastName(customerDTO.getLastName());
		
		if(customerDTO.getEmail()!="")
			customer.setEmail(customerDTO.getEmail());
		
		if(customerDTO.getAddressLine1()!="")
			customer.setAddressLine1(customerDTO.getAddressLine1());
		
		if(customerDTO.getAddressLine2()!="")
			customer.setAddressLine2(customerDTO.getAddressLine2());
		
		if(customerDTO.getCity()!="")
			customer.setCity(customerDTO.getCity());
		
		if(customerDTO.getState()!="")
			customer.setState(customerDTO.getState());
		
		if(customerDTO.getCountry()!="")
			customer.setCountry(customerDTO.getCountry());
		
		if(customerDTO.getPostalCode()!="")
			customer.setPostalCode(customerDTO.getPostalCode());
		
		return "Updated";
	}

}
