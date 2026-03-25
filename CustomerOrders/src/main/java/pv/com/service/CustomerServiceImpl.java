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
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pv.com.controller.CustomerController;
import pv.com.dto.CustomerDTO;
import pv.com.dto.OrderDTO;
import pv.com.dto.UpdateCustomerDTO;
import pv.com.dto.UpdateOrderDTO;
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
	
	@Autowired
	private Environment environment;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
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
	public String createOrder(Long customerId, OrderDTO orderDTO) throws CustomerOrderExceptions {

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
	    BigDecimal amt = calAmt(orders.getQuantity());
	    orders.setTotalAmount(amt);
//	    //end---------
	    orders.setCustomer(customer);  
	    customer.getOrders().add(orders);

	    orderRepository.save(orders); 

	    return "success "+orders.getOrderId();
	}

	@Override
	public List<OrderDTO> getOrders(Long customerId) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Customer> opt= customerRepository.findById(customerId);
		Customer customer = opt.orElseThrow(()->new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));
		List<OrderDTO> ordersDTOList = new ArrayList<>();
		for(Orders ol: customer.getOrders())
		{
			OrderDTO dto = new OrderDTO();
			dto = modelMapper.map(ol,OrderDTO.class);
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
	public CustomerDTO updateCustomer(Long Id, UpdateCustomerDTO customerDTO) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Customer> opt= customerRepository.findById(Id);
		Customer customer = opt.orElseThrow(()->new CustomerOrderExceptions(environment.getProperty("Service.CUSTOMER_NOT_FOUND")));
		if(customerDTO.getFirstName()!=null)
			customer.setFirstName(customerDTO.getFirstName());
		
		if(customerDTO.getLastName()!=null)
			customer.setLastName(customerDTO.getLastName());
		
		if(customerDTO.getEmail()!=null)
			customer.setEmail(customerDTO.getEmail());
		
		if(customerDTO.getAddressLine1()!=null)
			customer.setAddressLine1(customerDTO.getAddressLine1());
		
		if(customerDTO.getAddressLine2()!=null)
			customer.setAddressLine2(customerDTO.getAddressLine2());
		
		if(customerDTO.getCity()!=null)
			customer.setCity(customerDTO.getCity());
		
		if(customerDTO.getState()!=null)
			customer.setState(customerDTO.getState());
		
		if(customerDTO.getCountry()!=null)
			customer.setCountry(customerDTO.getCountry());
		
		if(customerDTO.getPostalCode()!=null)
			customer.setPostalCode(customerDTO.getPostalCode());
		
		customerRepository.save(customer);
		
		CustomerDTO cdto = modelMapper.map(customer, CustomerDTO.class);
		
		return cdto;
	}
	@Override
	public List<CustomerDTO> getCustomers() throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		
		List<Customer> list = customerRepository.findAll();
		List<CustomerDTO> dtolist = new ArrayList<>();
		for(Customer customer:list)
		{
			CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
			dtolist.add(dto);
		}
		return dtolist;
	}
	@Override
	public OrderDTO updateOrder(Long orderId, UpdateOrderDTO orderDTO) throws CustomerOrderExceptions {
		// TODO Auto-generated method stub
		Optional<Orders> opt = orderRepository.findById(orderId);
		Orders order=opt.orElseThrow(()-> new CustomerOrderExceptions(environment.getProperty("Service.ORDER_NOT_FOUND")));
		
		if(orderDTO.getQuantity()!=null)
			order.setQuantity(orderDTO.getQuantity());
		if(orderDTO.getDeliveryAddress()!=null)
			order.setDeliveryAddress(orderDTO.getDeliveryAddress());
		if(orderDTO.getOrderDate()!=null)
			order.setOrderDate(orderDTO.getOrderDate());
		if(orderDTO.getOrderStatus()!=null)
			order.setOrderStatus(orderDTO.getOrderStatus());
		if(orderDTO.getPaymentMode()!=null)
			order.setPaymentMode(orderDTO.getPaymentMode());
		if(orderDTO.getTotalAmount()!=null)
			order.setTotalAmount(orderDTO.getTotalAmount());
		if(order.getTransactionId()==null && !order.getPaymentMode().equals(PaymentMode.CASH)) {
		String timestamp = LocalDateTime.now().toString();
		String tranId = timestamp.replaceAll("[^a-zA-Z0-9T]", "");
		order.setTransactionId(tranId);
		}
		BigDecimal amt = calAmt(orderDTO.getQuantity());
		order.setTotalAmount(amt);
		order.setPaymentStatus(PaymentStatus.SUCESS);
		orderRepository.save(order);
		OrderDTO existingorderDTO = modelMapper.map(order, OrderDTO.class);
		return existingorderDTO;
	}
	public BigDecimal calAmt(Integer quantity)
	{
		BigDecimal q = BigDecimal.valueOf(quantity);
	    BigDecimal price = BigDecimal.valueOf(1200);
	    BigDecimal amt = q.multiply(price);
		return amt;
	}

}
