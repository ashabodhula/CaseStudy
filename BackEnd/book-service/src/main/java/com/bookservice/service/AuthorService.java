package com.bookservice.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.model.Author;
import com.bookservice.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepository;

	public Iterable<Author> getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveAuthor(@Valid Author author) {
		// TODO Auto-generated method stub
		
	}
	
	

}