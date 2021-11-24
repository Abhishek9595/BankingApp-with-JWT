//package com.technoelevate.springboot;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.technoelevate.springboot.entity.Customer;
//import com.technoelevate.springboot.repository.BalanceDetailsRepo;
//import com.technoelevate.springboot.repository.CustomerRepository;
//import com.technoelevate.springboot.service.CustomerServiceImpl;
//@SpringBootTest
//public class CustomerTestService {
//	@Mock
//	private CustomerRepository repository;
//	@Mock
//	private BalanceDetailsRepo balanceRepo;
//	@InjectMocks
//	private CustomerServiceImpl customerServiceImpl;
//	
//	@Test
//	public void loginTestService() {
//		Customer customer = new Customer("Sahid", 58425878552l, "sahid@123", 10000, 0);
//		Mockito.when(repository.findByUserName(customer.getUserName())).thenReturn(customer);
//		Customer customer1 = (Customer) customerServiceImpl.findByUserName(customer.getUserName(), customer.getPassword()).getData();
//		assertEquals(customer.getUserName(), customer1.getUserName());
//	}
//	@Test
//	public void depositeTestService() {
//		Customer customer = new Customer("Sahid", 58425878552l, "sahid@123", 10000, 0);
//		Mockito.when(repository.findByUserName(customer.getUserName())).thenReturn(customer);
//		Customer customer1 = (Customer)customerServiceImpl.deposite(1000).getData();
//		assertEquals(customer.getUserName(), customer1.getUserName());
//	}
//	@Test
//	public void withdrawTestService() {
//		Customer customer = new Customer("Sahid", 58425878552l, "sahid@123", 10000, 0);
//		Mockito.when(repository.findByUserName(customer.getUserName())).thenReturn(customer);
//		Customer customer1 = (Customer) customerServiceImpl.withdraw(1000).getData();
//		assertEquals(customer.getUserName(), customer1.getUserName());
//	}
//	@Test
//	public void balanceTestService() {
//		Customer customer = new Customer("Sahid", 58425878552l, "sahid@123", 10000, 0);
//		Mockito.when(repository.findByUserName(customer.getUserName())).thenReturn(customer);
//		Customer customer1 = (Customer) customerServiceImpl.findByUserName(customer.getUserName(), customer.getPassword()).getData();
//		assertEquals(customer.getUserName(), customer1.getUserName());
//	}
//	
//	
//}
