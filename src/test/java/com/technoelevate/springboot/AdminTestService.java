//package com.technoelevate.springboot;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.technoelevate.springboot.entity.Admin;
//import com.technoelevate.springboot.entity.Customer;
//import com.technoelevate.springboot.message.Message;
//import com.technoelevate.springboot.repository.AdminRepository;
//import com.technoelevate.springboot.repository.CustomerRepository;
//import com.technoelevate.springboot.service.AdminServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//public class AdminTestService {
//	@Mock
//	private AdminRepository adminRepository;
//	@Mock
//	private CustomerRepository repository;
//	@InjectMocks
//	private AdminServiceImpl service;
//
//	@Test
//	public void loginTestService() {
//		Admin admin1 = new Admin("Rakesh", "rakesh@123");
//		Mockito.when(adminRepository.findByUserName(admin1.getUserName())).thenReturn(admin1);
//		Admin admin2 = (Admin) service.findByUserName(admin1.getUserName(), admin1.getPassword()).getData();
//		assertEquals(admin1.getUserName(), admin2.getUserName());
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testServiceReadAll() {
//		Admin admin = new Admin("Rakesh", "rakesh@123");
//		List<Customer> list = new ArrayList<Customer>();
//		Customer customer = new Customer("Sahid", 8754854754l, "sahid@123", 10000, 0);
//		list.add(customer);
//		Mockito.when(repository.findAll()).thenReturn(list);
//		Message message = service.getAllCustomer(admin);
//		ArrayList<Customer> customer1=(ArrayList<Customer>)message.getData();
//		assertEquals(customer.getUserName(),customer1.get(0).getUserName());
//	}
//
//}
