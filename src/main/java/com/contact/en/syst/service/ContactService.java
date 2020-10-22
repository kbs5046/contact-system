package com.contact.en.syst.service;

import java.util.List;
import com.contact.en.syst.model.CallContact;
import com.contact.en.syst.model.Contact;


public interface ContactService {
	public List<Contact> getContacts(); 
	
	public void createContact(Contact contact);  
	
	public void updateContact(Contact contact); 
	
	public Contact getContact(Integer id);
	
	public void deleteContact(Integer id); 
	
	public List<CallContact> getContactCallList(); 
	
}
