package com.contact.en.syst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contact.en.syst.model.CallContact;
import com.contact.en.syst.model.Contact;
import com.contact.en.syst.service.ContactService;


@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;  
	
	@GetMapping("/contacts")
	public List<Contact> getContacts() {
		return contactService.getContacts(); 
	}
	
	@PostMapping("/contacts")
	public void createContact(@RequestBody Contact contact) {
		contactService.createContact(contact); 
	}
	
	@PutMapping("/contacts/{id}")
	public void updateContact(@PathVariable Integer id, @RequestBody Contact contact) {
		contact.setId(id);
		contactService.updateContact(contact);
	}
	
	@GetMapping("/contacts/{id}")
	public Contact getContact(@PathVariable Integer id) {
		return contactService.getContact(id);
	}
	
	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable Integer id) {
		contactService.deleteContact(id);
	}
	
	@GetMapping("/contacts/call-list")
	public List<CallContact> getContactCallList() {
		return contactService.getContactCallList(); 
	}
}
