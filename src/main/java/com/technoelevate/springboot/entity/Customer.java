package com.technoelevate.springboot.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({ "cid", "password", "balance", "count" })
@Table(name = "customer_details", uniqueConstraints = @UniqueConstraint(columnNames = { "userName", "accountNo" }))
public class Customer implements Serializable {
	@Id
	@SequenceGenerator(name = "mySeq_Customer", initialValue = 600, allocationSize = 100)
	@GeneratedValue(generator = "mySeq_Customer")
	private int cid;
	private String userName;
	private long accountNo;
	private String password;
	private double balance;
	private int count;
	@OneToMany( mappedBy = "customer")
	private List<BalanceDetails> balanceDetails;
	public Customer(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public Customer(String userName, long accountNo, String password, double balance, int count) {
		this.userName = userName;
		this.accountNo = accountNo;
		this.password = password;
		this.balance = balance;
		this.count = count;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", userName=" + userName + ", accountNo=" + accountNo + ", password=" + password
				+ ", balance=" + balance + ", count=" + count + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<BalanceDetails> getBalanceDetails() {
		return balanceDetails;
	}
	public void setBalanceDetails(List<BalanceDetails> balanceDetails) {
		this.balanceDetails = balanceDetails;
	}
	public Customer() {
	}
	
}
