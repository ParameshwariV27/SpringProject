package pv.com.service;

import java.util.List;

import pv.com.dto.CustomerDTO;
import pv.com.dto.OrdersDTO;
import pv.com.exceptions.CustomerOrderExceptions;

public interface CustomerService {

	public String CreateCustomer(CustomerDTO customerDTO)throws CustomerOrderExceptions;
	public CustomerDTO findCustomer(Long Id)throws CustomerOrderExceptions;
	public void deleteCustomer(Long Id)throws CustomerOrderExceptions;
	public String updateCustomer(Long Id, CustomerDTO customerDTO)throws CustomerOrderExceptions;
	
	//Orders
	public String createOrder(Long customerId, OrdersDTO orderDTO)throws CustomerOrderExceptions;
	public List<OrdersDTO> getOrders(Long customerId) throws CustomerOrderExceptions; 
}
