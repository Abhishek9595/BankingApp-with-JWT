package com.technoelevate.springboot.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttribute;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.springboot.entity.Customer;
//import com.technoelevate.springboot.exception.CustomerException;
import com.technoelevate.springboot.message.Message;
import com.technoelevate.springboot.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/api/customer")
@Api(value = "/api/v1/customer", tags = "Bank Application")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

//	@PostMapping(path = "/login")
//	public ResponseEntity<Message> loginCustomer(@RequestBody UserPasswordDTO dto) {
//		Message message = customerService.findByUserName(dto.getUserName(), dto.getPassword());
//		return new ResponseEntity<Message>(message, HttpStatus.OK);
//	}

	@PutMapping(path = "/withdraw/{amount}")
	@ApiOperation(value = "Withdraw Money", notes = "Withdraw Money", tags = "Bank Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Withdrawal Successfully...."),
	@ApiResponse(code = 404, message = "Invalid Customer"),
	@ApiResponse(code = 403, message = "Access Denied....") })
	public ResponseEntity<Message> withdraw(@PathVariable("amount") double amount) {
		LOGGER.info(amount + "Amount Successfully Withdrawn");
		return new ResponseEntity<Message>(customerService.withdraw(amount), HttpStatus.OK);
	}

	@PutMapping(path = "/deposit/{amount}")
	@ApiOperation(value = "Deposit money", notes = "Deposit Money", tags = "Bank Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deposit Successful...."),
	@ApiResponse(code = 404, message = "Invalid Customer"),
	@ApiResponse(code = 403, message = "Access Denied....") })
	public ResponseEntity<Message> deposit(@PathVariable("amount") double amount) {
		LOGGER.info(amount + "Amount Successfully Deposited  ");
		return new ResponseEntity<Message>(customerService.deposit(amount), HttpStatus.OK);
	}

	@GetMapping("/balance")
	@ApiOperation(value = "Balance Details", notes = "Balance Details", tags = "Bank Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Balance Successfully Fetched...."),
	@ApiResponse(code = 404, message = "No Customer Found"),
	@ApiResponse(code = 403, message = "Access Denied....") })
	public ResponseEntity<Message> getBalance() {
		return new ResponseEntity<Message>(customerService.getBalance(), HttpStatus.OK);
	}

//	@GetMapping("/logout")
//	public ResponseEntity<Message> logoutCustomer(HttpSession session,
//			@SessionAttribute(name = "customer", required = false) Customer customer) {
//		if (customer == null) {
//			LOGGER.error("Please Login First!!!");
//			throw new CustomerException("Please Login First!!!");
//		}
//		Message message = new Message(HttpStatus.OK.value(),new Date(),false, "Successfully Log Out " + customer.getUserName(), customer);
//		LOGGER.info("Successfully Log Out " + customer.getUserName());
//		session.invalidate();
//		return new ResponseEntity<Message>(message, HttpStatus.OK);
//	}
	
	@GetMapping("/token/refresh")
	@ApiOperation(value = "Generate new Access Token", notes = "Generate new Access Token", tags = "Bank Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "New Access Token Generated Successfully...."),
	@ApiResponse(code = 404, message = "Refresh Token is Missing"),
	@ApiResponse(code = 403, message = "Access Denied....") })
	public void refreashToken(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		String header = request.getHeader(AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			try {
				String refresh_token = header.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Customer customer = customerService.findByUserName(username);
				String access_token = JWT.create().withSubject(customer.getUserName())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURI().toString())
						.withClaim("roles",new ArrayList<>().add(new SimpleGrantedAuthority("USER")))
						.sign(algorithm);
				HashMap<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception exception) {
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value());
				HashMap<String, String> error = new HashMap<>();
				error.put("error", exception.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else
			throw new RuntimeException("Refresh token is missing");
		
		
	}

}
