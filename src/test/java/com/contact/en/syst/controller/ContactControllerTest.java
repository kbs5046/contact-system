package com.contact.en.syst.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.contact.en.syst.dao.ContactDaoService;
import com.contact.en.syst.model.Address;
import com.contact.en.syst.model.Contact;
import com.contact.en.syst.model.Name;
import com.contact.en.syst.model.Phone;
import com.contact.en.syst.model.PhoneType;
import com.contact.en.syst.service.ContactService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ContactController.class)
public class ContactControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContactService contactService;

	@MockBean
	private ContactDaoService contactDaoService;
	
	private List<Contact> allContacts = new ArrayList<>();
	
	private String expectedJsonFromContactList; 
	
	private Contact contact;  
	
	private String jsonCreateContact; 
	
	@Before
	public void init() {
		//Get contact list setup  
		contact = getContactData(); 	
		allContacts.add(contact);
		
		expectedJsonFromContactList = "[{\"name\":{\"first\":\"Jack\",\"middle\":\"B\",\"last\":\"Den\"},\"address\":{\"street\":\"s1\",\"city\":\"c1\",\"state\":\"z1\",\"zip\":\"st1\"},\"phone\":[{\"number\":\"980\",\"type\":\"home\"}],\"email\":\"jack@gmail.com\"}]";
		jsonCreateContact = "{\"name\":{\"first\":\"Jack\",\"middle\":\"B\",\"last\":\"Den\"},\"address\":{\"street\":\"s1\",\"city\":\"c1\",\"state\":\"z1\",\"zip\":\"st1\"},\"phone\":[{\"number\":\"980\",\"type\":\"home\"}],\"email\":\"jack@gmail.com\"}";
	}

	@Test
	public void testGetContacts() throws Exception {
		Mockito.when(contactService.getContacts()).thenReturn(allContacts);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contacts").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedJsonFromContactList, result.getResponse().getContentAsString(), true);
	}
	
	
	@Test
	public void testCreateContact() throws Exception {
		doNothing().when(contactService).createContact(Mockito.any(Contact.class));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contacts").accept(MediaType.APPLICATION_JSON)
				.content(jsonCreateContact).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	private Contact getContactData() {
		Contact contact = new Contact();
		contact.setName(new Name("Jack", "B", "Den"));
		contact.setEmail("jack@gmail.com");
		contact.setAddress(new Address("s1", "c1", "z1", "st1"));
		Phone p = new Phone();
		p.setNumber("980");
		p.setType(PhoneType.home);
		contact.setPhone(Arrays.asList(p));
		return contact;
	}
}
