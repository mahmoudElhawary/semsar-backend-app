package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.UserMessages;

public interface UserMessagesService {

	List<UserMessages> findAll() ;
	
	void delete(Long id) ;
	
	void deleteAll() ;
	
	List<UserMessages> findAllByUserIdOrderByMessageDateDesc(Long id) ;
	
	List<UserMessages> findAllBySenderIdOrderByMessageDateDesc(Long id) ;
	
	List<UserMessages> findAllByUserIdAndSenderIdOrderByMessageDateDesc(Long userId , Long senderId) ; 
	
	List<UserMessages> findAllBySenderIdAndUserIdOrderByMessageDateDesc( Long senderId,Long userId) ;
	
	UserMessages save(UserMessages messages) ;
	
	UserMessages findById(Long id) ;
	
	
}
