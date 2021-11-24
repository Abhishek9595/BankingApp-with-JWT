package com.technoelevate.springboot.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO implements Serializable {
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserPasswordDTO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public UserPasswordDTO() {
		
	}
	@Override
	public String toString() {
		return "UserPasswordDTO [userName=" + userName + ", password=" + password + "]";
	}
	
}
