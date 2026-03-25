package pv.com.service;

import java.util.List;

import pv.com.dto.CustomerDTO;
import pv.com.dto.OrderDTO;
import pv.com.dto.UpdateCustomerDTO;
import pv.com.dto.UpdateOrderDTO;
import pv.com.exceptions.CustomerOrderExceptions;

public interface CustomerService {

	public String CreateCustomer(CustomerDTO customerDTO)throws CustomerOrderExceptions;
	public CustomerDTO findCustomer(Long Id)throws CustomerOrderExceptions;
	public void deleteCustomer(Long Id)throws CustomerOrderExceptions;
	public CustomerDTO updateCustomer(Long Id, UpdateCustomerDTO customerDTO)throws CustomerOrderExceptions;
	public List<CustomerDTO> getCustomers() throws CustomerOrderExceptions;
	public OrderDTO updateOrder(Long orderId, UpdateOrderDTO orderDTO) throws CustomerOrderExceptions;
	
	//Orders
	public String createOrder(Long customerId, OrderDTO orderDTO)throws CustomerOrderExceptions;
	public List<OrderDTO> getOrders(Long customerId) throws CustomerOrderExceptions; 
}
