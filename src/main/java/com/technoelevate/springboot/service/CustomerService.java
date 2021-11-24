package com.technoelevate.springboot.service;

import com.technoelevate.springboot.entity.Customer;
import com.technoelevate.springboot.message.Message;

public interface CustomerService {

//	Message findByUserName(String userName, String password);

	Customer findByUserName(String userName);

	Message deposit(double amount);

	Message withdraw(double amount);

	Message getBalance();

}
