package com.technoelevate.springboot.controller;

//import java.util.Date;

//import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttribute;

//import com.technoelevate.springboot.entity.Admin;
//import com.technoelevate.springboot.exception.CustomerException;
import com.technoelevate.springboot.message.Message;
import com.technoelevate.springboot.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/api/admin")
@Api(value = "/api/v1/admin", tags = "Bank Application")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	

//	@PostMapping(path = "/login")
//	public ResponseEntity<Message> loginAdmin(@RequestBody UserPasswordDTO dto, HttpServletRequest req) {
//		Message message = adminService.findByUserName(dto.getUserName(), dto.getPassword());
//		HttpSession session = req.getSession();
//		session.setAttribute("admin", (Admin) message.getData());
//		return new ResponseEntity<Message>(message, HttpStatus.OK);
//	}

	@GetMapping(path = "/customers")
	@ApiOperation(value = "All Customers", notes = "All Customers", tags = "Bank Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetched Successfully...."),
	@ApiResponse(code = 404, message = "No Customer Found"),
	@ApiResponse(code = 403, message = "Access Denied....") })
	public ResponseEntity<Message> getAllCustomer() {
		LOGGER.info("FETCHED SUCCESSFULLY....");
		return new ResponseEntity<Message>(adminService.getAllCustomer(), HttpStatus.OK);
	}

//	@GetMapping("/logout")
//	public ResponseEntity<Message> logoutAdmin(HttpSession session, @SessionAttribute(name = "admin", required = false) Admin admin) {
//		if (admin == null) {
//			LOGGER.error("Please Login First!!!");
//			throw new CustomerException("Please Login First!!!");
//		}
//		LOGGER.info("Successfully Log Out " + admin.getUserName());
//		Message message = new Message(HttpStatus.OK.value(),new Date(),false, "Successfully Log Out " + admin.getUserName(), admin);
//		session.invalidate();
//		return new ResponseEntity<Message>(message, HttpStatus.OK);
//	}
}
