package com.elhawary.semsar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhawary.semsar.model.ContactUs;
import com.elhawary.semsar.repository.ContactUsRepository;

@Service
@Transactional
public class ContactUsServicImpl implements ContactUsService{

	@Autowired
	private ContactUsRepository contactUsRepository ;

	@Override
	public List<ContactUs> findAll() {
		return contactUsRepository.findAll();
	}

	@Override
	public ContactUs save(ContactUs contactUs) {
		contactUs.setCreateDate(new Date());
		return contactUsRepository.save(contactUs);
	}
}
