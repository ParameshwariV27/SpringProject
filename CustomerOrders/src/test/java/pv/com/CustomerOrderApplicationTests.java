package pv.com;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import pv.com.dto.CustomerDTO;
import pv.com.entity.Customer;
import pv.com.exceptions.CustomerOrderExceptions;
import pv.com.repository.CustomerRepository;
import pv.com.service.CustomerService;
import pv.com.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerOrderApplicationTests {
	
	@Mock
	private CustomerRepository repo;
	
	@InjectMocks
	private CustomerServiceImpl service;
	
	@Mock
    private ModelMapper modelMapper;

	@Test
	public void testFindCustomer() throws CustomerOrderExceptions {
		
		//
		//String firstName, String lastName, String email, String addressLine1,
        //String addressLine2, String city, String state, String country, String postalCode
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("Piku");
		customer.setLastName("Sharma");
		customer.setEmail("ps@gmail.com");
		customer.setAddressLine1("DVG Road");
		customer.setAddressLine2("Bangalore");
		customer.setCity("Bangalore");
		customer.setCountry("India");
		customer.setPostalCode("560098");
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1L);
		customerDTO.setFirstName("Piku");
		customerDTO.setLastName("Sharma");
		customerDTO.setEmail("ps@gmail.com");
		customerDTO.setAddressLine1("DVG Road");
		customerDTO.setAddressLine2("Bangalore");
		customerDTO.setCity("Bangalore");
		customerDTO.setCountry("India");
		customerDTO.setPostalCode("560098");
		
		when(repo.findById(1L))
        .thenReturn(Optional.of(customer));
		
		when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);
		
		CustomerDTO resDTO = service.findCustomer(1L);
		
		assertEquals(customerDTO, resDTO);
		verify(repo).findById(1L);
	}

	
}
