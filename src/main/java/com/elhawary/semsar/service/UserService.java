package com.elhawary.semsar.service;

import java.util.List;

import com.elhawary.semsar.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	Iterable<User> getUsers() ;
	
	User getUserByEmail(String email);

	User getUser(Long id);

	User updateUser(User userDB);

	User findOne(Long id);

}
