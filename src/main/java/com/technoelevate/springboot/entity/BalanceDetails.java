package com.technoelevate.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "bid", "customer" })
public class BalanceDetails implements Serializable {
	@Id
	@SequenceGenerator(name = "mySeq_BalanceDetails", initialValue = 100, allocationSize = 100)
	@GeneratedValue(generator = "mySeq_BalanceDetails")
	private int bid;
	private double debit;
	private double credit;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date date;
	private double balance;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public BalanceDetails(double debit, double credit, Date date, double balance, Customer customer) {
		this.debit = debit;
		this.credit = credit;
		this.date = date;
		this.balance = balance;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "BalanceDetails [bid=" + bid + ", debit=" + debit + ", credit=" + credit + ", date=" + date
				+ ", balance=" + balance + "]";
	}

	public BalanceDetails() {
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
