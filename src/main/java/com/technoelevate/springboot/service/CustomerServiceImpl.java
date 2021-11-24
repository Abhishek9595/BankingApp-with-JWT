package com.technoelevate.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.technoelevate.springboot.entity.Admin;
import com.technoelevate.springboot.entity.BalanceDetails;
import com.technoelevate.springboot.entity.Customer;
import com.technoelevate.springboot.exception.CustomerException;
import com.technoelevate.springboot.message.Message;
import com.technoelevate.springboot.repository.AdminRepository;
import com.technoelevate.springboot.repository.BalanceDetailsRepo;
import com.technoelevate.springboot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private BalanceDetailsRepo balanceRepo;
	@Autowired
	private AdminRepository adminRepository;
	private Customer customer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (!username.equals("Rakesh")) {
			this.customer = repository.findByUserName(username);
			if (customer == null) {
				LOGGER.error("User Not in Data Base");
				throw new CustomerException("Please Enter your Correct User Name");
			}
			authorities.add(new SimpleGrantedAuthority("USER"));
			return new User(customer.getUserName(), customer.getPassword(), authorities);
		} else {
			Admin admin = adminRepository.findByUserName(username);
			if (admin == null) {
				LOGGER.error("User Not in Data Base");
				throw new CustomerException("Please Enter your Correct User Name");
			}
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new User(admin.getUserName(), admin.getPassword(), authorities);
		}
	}

//	@Override
//	public Message findByUserName(String userName, String password) {
//		Customer customer =(Customer) repository.findByUserName(userName);
//		String encode = encoder.encode(password);
//		System.out.println(encode);
//		System.out.println(customer);
//
//		if (customer != null) {
//			if (customer.getPassword().equals(encode)) {
//				log.info("Successfully Logged in " + userName);
//				return new Message(HttpStatus.OK.value(), new Date(), false, "Successfully Logged in " + userName,
//						customer);
//			}
//			log.error("Please Enter your Correct Password");
//			throw new CustomerException("Please Enter your Correct Password");
//		}
//		log.error("Please Enter your Correct User Name");
//		throw new CustomerException("Please Enter your Correct User Name");
//	}

	@Value("${deposite.tax}")
	double deposite_tax;

	@Override
	public Message deposit(double amount) {
		double availableAmount = (double) Math.round((customer.getBalance() + amount * (1 - deposite_tax)) * 1000.0)
				/ 1000.0;
		if (amount % 100 == 0 && amount > 0) {
			customer.setBalance(availableAmount);
			this.repository.save(customer);
			this.balanceRepo.save(new BalanceDetails(amount, 0, new Date(), availableAmount, customer));
			System.out.println(this.repository);
			Customer customer2 = (Customer) this.repository.findByUserName(customer.getUserName());
			return new Message(HttpStatus.OK.value(), new Date(), false, amount + " Amount Successfully Deposited  ",
					customer2);
		}
		LOGGER.error("The Amount Should be Multiple of 100");
		throw new CustomerException("The Amount Should be Multiple of 100");
	}

	@Value("${withdraw.tax}")
	double withdraw_tax;

	@Override
	public Message withdraw(double amount) {
		double availableAmount = (double) Math.round((customer.getBalance() - amount * (1 + withdraw_tax)) * 1000.0)
				/ 1000.0;
		if (amount % 100 != 0) {
			LOGGER.error("The Amount Should be Multiple of 100");
			throw new CustomerException("The Amount Should be Multiple of 100");
		}
		if (availableAmount > 0 && customer.getBalance() > 500) {
			if (customer.getCount() < 3) {
				customer.setBalance(availableAmount);
				customer.setCount(customer.getCount() + 1);
				repository.save(customer);
				balanceRepo.save(new BalanceDetails(0, amount, new Date(), availableAmount, customer));
				Customer customer2 = (Customer) repository.findByUserName(customer.getUserName());
				return new Message(HttpStatus.OK.value(), new Date(), false, amount + " Amount Successfully Withdrawn ",
						customer2);
			}
			LOGGER.error(" Only 3 times Can be withdrawn in a month!!!");
			throw new CustomerException(" Only 3 times Can be withdrawn in a month!!!");
		}
		LOGGER.error("Insufficient Balance!!!");
		throw new CustomerException("Insufficient Balance!!!");
	}

	@Override
	public Message getBalance() {
		if (customer == null || customer.getUserName() == null) {
			throw new CustomerException("Please Login First!!!");
		}
		Customer customer2 = (Customer) repository.findByUserName(customer.getUserName());
		return new Message(HttpStatus.OK.value(), new Date(), false, "Your Balance is : " + customer2.getBalance(),
				customer2);
	}

	@Override
	public Customer findByUserName(String userName) {
		Customer customer = (Customer) repository.findByUserName(userName);
		LOGGER.info("Successfully Logged in " + userName);
		return customer;
	}
}
