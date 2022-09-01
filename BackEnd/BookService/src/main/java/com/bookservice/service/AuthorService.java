package com.bookservice.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bookservice.entity.Author;
import com.bookservice.repository.AuthorRepository;


@Service
public class AuthorService {
	@Autowired
	AuthorRepository authorRepository;
	
	public Iterable<Author> getAuthor() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}


	public Author saveAuthor( Author author) {
		// TODO Auto-generated method stub
		authorRepository.save(author);
		return author;
	}



	
}
