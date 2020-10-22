package com.contact.en.syst.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.en.syst.dao.ContactDaoService;
import com.contact.en.syst.model.CallContact;
import com.contact.en.syst.model.Contact;
import com.contact.en.syst.model.Name;
import com.contact.en.syst.model.Phone;
import com.contact.en.syst.model.PhoneType;
import com.contact.en.syst.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDaoService contactDaoService;
	
	private Predicate<Phone> homePhone = p->p.getType().equals(PhoneType.home);
	
	private Function<CallContact, String> contactLastName = c->c.getName().getLast();
	
	private Function<CallContact, String> contactFirstName = c->c.getName().getFirst();

	@Override
	public List<Contact> getContacts() {
		return contactDaoService.findAll();
	}

	@Override
	public void createContact(Contact contact) {
		contactDaoService.save(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		contactDaoService.save(contact);
	}

	@Override
	public Contact getContact(Integer id) {
		return contactDaoService.findById(id).get();
	}

	@Override
	public void deleteContact(Integer id) {
		contactDaoService.deleteById(id);
	}
	
	@Override
	public List<CallContact> getContactCallList() {
		List<CallContact> contacts = getContacts().stream()
				.filter(a -> a.getPhone().stream().anyMatch(homePhone))
				.map(c -> new CallContact(
						new Name(c.getName().getFirst(), c.getName().getMiddle(), c.getName().getLast()),
						getHomePhone(c)))
				.collect(Collectors.toList());

		List<CallContact> results = contacts.stream()           
				.sorted(Comparator.comparing(contactLastName)
						.thenComparing(contactFirstName))
				.collect(Collectors.toList());
		
		return results;
	}
	
	private String getHomePhone(Contact c) {
		return c.getPhone().
				stream().
				filter(homePhone).
				map(p -> p.getNumber()).
				collect(Collectors.toList()).get(0); 
	}

}
