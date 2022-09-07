package com.bookservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Author;
import com.bookservice.model.Book;
import com.bookservice.model.Reader;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.ReaderRepository;
import com.bookservice.service.AuthorService;
import com.bookservice.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/digitalbooks")

public class AuthorController {

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/author/addbook")
	Book addBook(@RequestBody @Valid Book book) {
		// System.out.println("book is added");

		return bookService.addBook(book);
	}

	@PostMapping(path = "/author/signup")
	public ResponseEntity registerReader(@RequestBody Author author) {
//		System.out.println("Registering... User ID : " + user.getUserName());

		if (authorRepository.existsByUsername(author.getUsername())) {
			return ResponseEntity.badRequest().body(" Username is already taken!");
		}

		if (authorRepository.existsByEmail(author.geteMail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}

		authorRepository.save(author);
		return ResponseEntity.ok(" Author SignUp success");

	}

	@PostMapping("/author/login")
	public ResponseEntity<?> loginUser(@RequestBody Author author) throws Exception {
		String tempEmailId = author.geteMail();
		String tempPassword = author.getPassword();

		Optional<Author> authorObj = authorRepository.findByEmailAndPassword(tempEmailId, tempPassword);
		if (authorObj.isPresent()) {
			return ResponseEntity.ok(" author Login success");
		}

		return ResponseEntity.badRequest().body("Error: Invalid Credential");
	}


	
//	@PostMapping(path = "/{authorId}/books")
//	Book createBook(@RequestBody Book book, @PathVariable("authorId") Integer authorId) {
//		
//		return bookService.addBook(book, authorId);

	}

	

	

