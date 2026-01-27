package pv.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pv.com.entity.Customer;
import pv.com.entity.Orders;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	List<Orders> findOrdersByCustomerId(Long customerId);
}
