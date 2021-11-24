//package com.technoelevate.springboot;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Map;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.technoelevate.springboot.controller.CustomerController;
//import com.technoelevate.springboot.entity.Customer;
//import com.technoelevate.springboot.entity.UserPasswordDTO;
//import com.technoelevate.springboot.message.Message;
//import com.technoelevate.springboot.service.CustomerService;
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//public class CustomerTestController {
//	@InjectMocks
//	private CustomerController customerController;
//	@Mock
//	private CustomerService customerService;
//	private MockMvc mockMvc;
//	private ObjectMapper mapper;
//
//	@BeforeEach
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
//		this.mapper = new ObjectMapper();
//	}
//
//	@SuppressWarnings({ "unchecked"})
//	@Test
//	public void testUserLogin() throws UnsupportedEncodingException, Exception {
//		UserPasswordDTO dto = new UserPasswordDTO("Sahid", "sahid@123");
//		Customer customer = new Customer(dto.getUserName(), dto.getPassword());
//		Message message = new Message();
//		message.setData(customer);
//		Mockito.when(customerService.findByUserName(Mockito.any(), Mockito.any())).thenReturn(message);
//		String jsonObject = mapper.writeValueAsString(dto);
//		String result = mockMvc
//				.perform(post("/customer/login").contentType(MediaType.APPLICATION_JSON).content(jsonObject)
//						.accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		Message message2 = mapper.readValue(result, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> dto1 : map.entrySet()) {
//			assertEquals(dto.getUserName(), dto1.getValue());
//			break;
//		}
//	}
//
//	@SuppressWarnings({ "unchecked"})
//	@Test
//	public void testDeposite() throws Exception {
//		UserPasswordDTO dto = new UserPasswordDTO("Sahid", "sahid@123");
//		Customer customer = new Customer(dto.getUserName(), dto.getPassword());
//		Message message = new Message();
//		message.setData(customer);
//		Mockito.when(customerService.deposite(Mockito.anyDouble())).thenReturn(message);
//		String jsonObject = mapper.writeValueAsString(dto);
//		String result = mockMvc
//				.perform(put("/customer/deposite/" + 1000).sessionAttr("customer", customer)
//						.contentType(MediaType.APPLICATION_JSON).content(jsonObject))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		System.out.println(result);
//		Message message2 = mapper.readValue(result, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> dto1 : map.entrySet()) {
//			assertEquals(customer.getUserName(), dto1.getValue());
//			break;
//		}
//	}
//
//	@SuppressWarnings({ "unchecked"})
//	@Test
//	public void testWidtdraw() throws Exception {
//		UserPasswordDTO dto = new UserPasswordDTO("Sahid", "sahid@123");
//		Customer customer = new Customer(dto.getUserName(), dto.getPassword());
//		Message message = new Message();
//		message.setData(customer);
//		Mockito.when(customerService.withdraw(Mockito.anyDouble())).thenReturn(message);
//		String jsonObject = mapper.writeValueAsString(dto);
//		String result = mockMvc
//				.perform(put("/customer/withdraw/" + 1000).sessionAttr("customer", customer)
//						.contentType(MediaType.APPLICATION_JSON).content(jsonObject))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		Message message2 = mapper.readValue(result, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> dto1 : map.entrySet()) {
//			assertEquals(customer.getUserName(), dto1.getValue());
//			break;
//		}
//	}
//
//	@SuppressWarnings({ "unchecked"})
//	@Test
//	public void testGetBalance() throws Exception {
//		UserPasswordDTO dto = new UserPasswordDTO("Sahid", "sahid@123");
//		Customer customer = new Customer(dto.getUserName(), dto.getPassword());
//		Message message1 = new Message();
//		message1.setData(customer);
//		Mockito.when(customerService.getBalance()).thenReturn(message1);
//		String jsonObject = mapper.writeValueAsString(dto);
//		String result = mockMvc
//				.perform(get("/customer/balance").sessionAttr("customer", customer)
//						.contentType(MediaType.APPLICATION_JSON).content(jsonObject))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		Message message2 = mapper.readValue(result, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> customer1 : map.entrySet()) {
//			assertEquals(customer.getUserName(), customer1.getValue());
//			break;
//		}
//	}
//
//	@SuppressWarnings({ "unchecked" })
//	@Test
//	public void logoutCustomer() throws Exception {
//		UserPasswordDTO dto = new UserPasswordDTO("Sahid", "sahid@123");
//		Customer customer = new Customer(dto.getUserName(), dto.getPassword());
//		Message message1 = new Message();
//		message1.setData(customer);
//		String jsonObject = mapper.writeValueAsString(dto);
//		String result = mockMvc
//				.perform(get("/customer/logout").sessionAttr("customer", customer)
//						.contentType(MediaType.APPLICATION_JSON).content(jsonObject))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		Message message2 = mapper.readValue(result, Message.class);
//		Map<String, String> map = (Map<String, String>) message2.getData();
//		for (Map.Entry<String, String> dto1 : map.entrySet()) {
//			assertEquals(dto.getUserName(), dto1.getValue());
//			break;
//		}
//	}
//}
