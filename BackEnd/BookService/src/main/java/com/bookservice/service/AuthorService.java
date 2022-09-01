package com.bookservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bookservice.entity.Author;


@Service
public class AuthorService {
	private static final Logger logger = 
			LoggerFactory.getLogger(AuthorService.class);
	

	public void saveCreateUserLog(Author author) {
		// TODO Auto-generated method stub
		logger.info(String.format("Author created -> %s", author));
		
	}

	
}
