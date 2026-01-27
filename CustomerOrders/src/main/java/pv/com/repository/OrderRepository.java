package pv.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pv.com.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

	

}
