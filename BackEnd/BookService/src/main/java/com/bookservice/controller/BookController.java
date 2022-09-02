package com.bookservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.Book;
import com.bookservice.service.BookService;



@RestController
@RequestMapping("/api/")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping
	Iterable<Book> getUser() {
		return bookService.getBooks();
	}
	
	@PostMapping
	Book saveBook(@Valid @RequestBody Book book) {
		bookService.save(book);
	return book;
	}

}
