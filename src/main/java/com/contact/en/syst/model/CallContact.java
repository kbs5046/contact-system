package com.contact.en.syst.model;

public class CallContact {
	private Name name; 
	private String phone;
	
	public CallContact(Name name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}  
	
	
}
