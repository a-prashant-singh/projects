package com.bank.bean;

import java.math.BigDecimal;

public class User {
	private int id;
	private String firstname, lastname, emailid, password, gender, dob, address, phone;
	private long accountno;
	private BigDecimal balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getAccountno() {
		return accountno;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public User(int id, String firstname, String lastname, String emailid, String password, String gender, String dob,
			String address, String phone, long accountno, BigDecimal balance) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailid = emailid;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.accountno = accountno;
		this.balance = balance;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", emailid=" + emailid
				+ ", password=" + password + ", gender=" + gender + ", dob=" + dob + ", address=" + address + ", phone="
				+ phone + ", accountno=" + accountno + ", balance=" + balance + "]";
	}
	
	
}
