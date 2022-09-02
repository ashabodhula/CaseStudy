package com.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Book;
import com.bookservice.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/addbook")
	List<Book> addBook(@RequestBody List<Book> books) {
		System.out.println("addBook called");

		return bookService.addBook(books);
	}

	@GetMapping("/getbookslist")
	List<Book> getBookList() {
		return bookService.getBookList();
	}

}
