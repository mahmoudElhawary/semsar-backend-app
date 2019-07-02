package com.egypt.daily.life.shopping.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserMessages;
import com.egypt.daily.life.shopping.service.UserMessagesService;
import com.egypt.daily.life.shopping.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MessageController {

	@Autowired
	private UserMessagesService userMessagesService;

	@Autowired
	private UserService userService;

	@GetMapping("/allMessages")
	public ResponseEntity<List<UserMessages>> allMessages() {
		List<UserMessages> messages = userMessagesService.findAll();
		return new ResponseEntity<List<UserMessages>>(messages, HttpStatus.OK);
	}

	@PostMapping("/sendMessage")
	public ResponseEntity<List<UserMessages>> sendMessage(@RequestParam("message") String message,
			@RequestParam("user") String user, @RequestParam("id") String id)
			throws JsonParseException, JsonMappingException, IOException {
		if (message != null) {
			// get product data from rest api
			UserMessages messageData = new ObjectMapper().readValue(message, UserMessages.class);
			long proId = new ObjectMapper().readValue(id, Long.class);
			User receiver = userService.findOne(proId);
			User userData = new ObjectMapper().readValue(user, User.class);

			messageData.setMessageDate(new Date());
			messageData.setSenderId(userData.getId());
//			messageData.setMessageFrom(userData);
			messageData.setUser(receiver);
			userMessagesService.save(messageData);
			List<UserMessages> messages = userMessagesService.findAllByUserIdAndSenderId(receiver.getId(),
					userData.getId());
			return new ResponseEntity<List<UserMessages>>(messages, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@PostMapping("/sendMessagePhoto")
	public ResponseEntity<List<UserMessages>> sendMessagePhoto(@RequestParam("messageFile") MultipartFile messageFile,
			@RequestParam("user") String user, @RequestParam("id") String id)
			throws JsonParseException, JsonMappingException, IOException {
		if (messageFile != null) {
			// get product data from rest api
			long proId = new ObjectMapper().readValue(id, Long.class);
			User receiver = userService.findOne(proId);
			User userData = new ObjectMapper().readValue(user, User.class);
			UserMessages messageData = new UserMessages() ;
			messageData.setMessageDate(new Date());
			messageData.setSenderId(userData.getId());
			messageData.setMessagePhoto(messageFile.getBytes());
			messageData.setUser(receiver);
			userMessagesService.save(messageData);
			List<UserMessages> messages = userMessagesService.findAllByUserIdAndSenderId(receiver.getId(),
					userData.getId());
			return new ResponseEntity<List<UserMessages>>(messages, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@PostMapping("/getMessages")
	public ResponseEntity<List<UserMessages>> getMessagesFromThisSender(@RequestParam("user") String user,
			@RequestParam("id") String id) throws JsonParseException, JsonMappingException, IOException {
		if (user != null) {
			// get product data from rest api
			long proId = new ObjectMapper().readValue(id, Long.class);
			User receiver = userService.findOne(proId);
			User userData = new ObjectMapper().readValue(user, User.class);

			List<UserMessages> messages = userMessagesService.findAllByUserIdAndSenderId(receiver.getId(),
					userData.getId());
			return new ResponseEntity<List<UserMessages>>(messages, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/findAllBySenderId/{id}")
	public ResponseEntity<List<UserMessages>> findAllBySenderId(@PathVariable("id") Long id) {
		if (id != null) {
			List<UserMessages> messages = userMessagesService.findAllBySenderId(id);
			return new ResponseEntity<List<UserMessages>>(messages, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/findAllByUserId/{id}")
	public ResponseEntity<List<UserMessages>> findAllByUserId(@PathVariable("id") Long id) {
		if (id != null) {
			List<UserMessages> messages = userMessagesService.findAllByUserId(id);
			return new ResponseEntity<List<UserMessages>>(messages, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/deleteMessage/{id}")
	public ResponseEntity<Response> deleteMessage(@PathVariable("id") Long id) {
		if (id != null) {
			userMessagesService.delete(id);
			return new ResponseEntity<Response>(new Response("this Message Deleted Successfully"), HttpStatus.OK);
		} else {
			return null;
		}
	}

	@GetMapping("/getMessage/{id}")
	public ResponseEntity<UserMessages> getMessage(@PathVariable("id") Long id) {
		if (id != null) {
			UserMessages userMessages = userMessagesService.findById(id);
			return new ResponseEntity<UserMessages>(userMessages, HttpStatus.OK);
		} else {
			return null;
		}
	}
}
