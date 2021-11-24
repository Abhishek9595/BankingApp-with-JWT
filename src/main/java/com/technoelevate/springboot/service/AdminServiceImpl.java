package com.technoelevate.springboot.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.technoelevate.springboot.entity.Customer;
import com.technoelevate.springboot.message.Message;
import com.technoelevate.springboot.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CustomerRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public Message getAllCustomer() {
		LOGGER.info("Successfully Fetched  ");
		List<Customer> listOfCustomer= repository.findAll();
		for (Customer customer : listOfCustomer) {
			customer.setBalance(0);
			customer.setBalanceDetails(null);
		}
		return new Message(HttpStatus.OK.value(),new Date(),false, "Successfully Fetched", listOfCustomer);
	}

}
