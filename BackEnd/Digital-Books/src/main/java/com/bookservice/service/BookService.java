package com.bookservice.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookservice.model.Book;
import com.bookservice.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Book addBook(Book book) {

		bookRepository.save(book);
		
		return book;
	}

	public List<Book> getBooks() {

		List<Book> books = bookRepository.findAll();// here need to show books which are active

		return books;
	}

//	public Book addBook(Book book, Integer authorId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
