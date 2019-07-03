package com.elhawary.semsar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elhawary.semsar.domain.Response;
import com.elhawary.semsar.model.ContactUs;
import com.elhawary.semsar.service.ContactUsService;

@RestController
public class ContactUsController {

	@Autowired
	private ContactUsService  contactUsService ;
	@GetMapping("/getMessages")
	public ResponseEntity<List<ContactUs>> getAllMessages() {
		List<ContactUs>users=contactUsService.findAll() ;
		return new ResponseEntity<List<ContactUs>>(users , HttpStatus.OK) ;
	}
	
	@PostMapping("/setMessages")
	public ResponseEntity<List<ContactUs>> setMessages(@RequestBody ContactUs contactUs) {
		if(contactUs != null) {
			contactUsService.save(contactUs) ;
			List<ContactUs> messages = contactUsService.findAll() ;
			return new ResponseEntity<List<ContactUs>>(messages, HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
}
