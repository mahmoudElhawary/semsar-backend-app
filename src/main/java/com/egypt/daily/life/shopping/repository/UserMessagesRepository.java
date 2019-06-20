package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.UserMessages;

@Repository
public interface UserMessagesRepository extends CrudRepository<UserMessages, Long>{

	List<UserMessages> findAllByUserIdOrderByMessageDateDesc(Long id) ;
	
	List<UserMessages> findByUserIdAndSenderIdOrderByMessageDateDesc(Long userId , Long senderId) ; 
	
	List<UserMessages> findBySenderIdAndUserIdOrderByMessageDateDesc( Long senderId,Long userId) ;
	
	List<UserMessages> findAllBySenderIdOrderByMessageDateDesc(Long id) ;
}
