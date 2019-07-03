package com.elhawary.semsar.service;

import java.util.List;

import com.elhawary.semsar.model.ContactUs;

public interface ContactUsService {

	List<ContactUs> findAll();

	ContactUs save(ContactUs contactUs);

}
