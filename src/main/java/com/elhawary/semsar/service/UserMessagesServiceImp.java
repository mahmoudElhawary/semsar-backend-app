package com.elhawary.semsar.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elhawary.semsar.model.UserMessages;
import com.elhawary.semsar.repository.UserMessagesRepository;

@Service
@Transactional
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
	public void deleteAll() {
		userMessagesRepository.deleteAll();
	}

	@Override
	public List<UserMessages> findAllByUserId(Long id) {
		return userMessagesRepository.findAllByUserIdOrderByMessageDateDesc(id);
	}

	@Override
	public List<UserMessages> findAllBySenderId(Long id) {
		return userMessagesRepository.findAllBySenderIdOrderByMessageDateDesc(id);
	}

	@Override
	public List<UserMessages> findAllByUserIdAndSenderId(Long userId, Long senderId) {
		return userMessagesRepository.findAllByUserIdAndSenderIdOrderByMessageDateDesc(userId, senderId);
	}

	@Override
	public UserMessages save(UserMessages messages) {
		return userMessagesRepository.save(messages);
	}

	@Override
	public UserMessages findById(Long id) {	
		return userMessagesRepository.findById(id).get();
	}
}
