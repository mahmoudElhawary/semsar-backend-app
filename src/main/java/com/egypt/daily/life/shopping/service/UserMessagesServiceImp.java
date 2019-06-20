package com.egypt.daily.life.shopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.UserMessages;
import com.egypt.daily.life.shopping.repository.UserMessagesRepository;

@Service
public class UserMessagesServiceImp implements UserMessagesService{

	@Autowired
	private UserMessagesRepository userMessagesRepository ;
	@Override
	public List<UserMessages> findAll() {

		return (List<UserMessages>) userMessagesRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userMessagesRepository.deleteById(id);
	}

	@Override
	public UserMessages save(UserMessages messages) {
		return userMessagesRepository.save(messages);
	}

	@Override
	public UserMessages findById(Long id) {
		return userMessagesRepository.findById(id).get();
	}

	@Override
	public void deleteAll() {
		userMessagesRepository.deleteAll();
	}

	@Override
	public List<UserMessages> findAllByUserIdOrderByMessageDateDesc(Long id) {
		return userMessagesRepository.findAllByUserIdOrderByMessageDateDesc(id);
	}

	@Override
	public List<UserMessages> findAllBySenderIdOrderByMessageDateDesc(Long id) {
		return userMessagesRepository.findAllBySenderIdOrderByMessageDateDesc(id);
	}

	@Override
	public List<UserMessages> findAllByUserIdAndSenderIdOrderByMessageDateDesc(Long userId, Long senderId) {
		return userMessagesRepository.findByUserIdAndSenderIdOrderByMessageDateDesc(userId, senderId);
	}

	@Override
	public List<UserMessages> findAllBySenderIdAndUserIdOrderByMessageDateDesc(Long senderId, Long userId) {
		return userMessagesRepository.findBySenderIdAndUserIdOrderByMessageDateDesc(senderId, userId);
	}

}
