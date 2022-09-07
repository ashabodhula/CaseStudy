package com.bookservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Book;
import com.bookservice.service.BookService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/digitalbooks")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/addbook")
	public Book addBook(@RequestBody @Valid Book book) {
	//	System.out.println("book is added");

		return bookService.addBook(book);
		
	}


}
