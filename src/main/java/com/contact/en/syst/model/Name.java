package com.contact.en.syst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "name")
public class Name {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer id;  
	
	@Column(name = "first")
	@JsonProperty("first")
	private String first;
	
	@Column(name = "middle")
	@JsonProperty("middle")
	private String middle;
	
	@Column(name = "last")
	@JsonProperty("last")
    private String last;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Name(String first, String middle, String last) {
		super();
		this.first = first;
		this.middle = middle;
		this.last = last;
	}  
	
	public Name() {
		
	}
	
	
    
}
