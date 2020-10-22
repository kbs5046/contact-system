package com.contact.en.syst.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.en.syst.model.Contact;

@Repository
public interface ContactDaoService extends JpaRepository<Contact, Integer>{
	
}
