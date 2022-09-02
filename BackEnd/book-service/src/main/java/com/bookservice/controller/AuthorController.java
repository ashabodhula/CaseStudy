package com.bookservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Author;
import com.bookservice.service.AuthorService;

@RestController

public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@GetMapping
	Iterable<Author> getBooks() {
		return authorService.getAuthor();
	}
	@PostMapping // author can create book
	Author saveAuthor(@Valid @RequestBody Author author) {

		authorService.saveAuthor(author);
		return author;
	}

	

}
