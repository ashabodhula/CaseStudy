package com.bookservice.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.entity.Book;
import com.bookservice.repository.BookRepository;
@Service
public class BookService {
	@Autowired
	
	BookRepository bookRepository;
	
	public Book CreateBook(Book book) { //author should have this permission
		
		bookRepository.save(book);
		return book;
		
	}
	public Iterable<Book> getBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}
	public Book save(@Valid Book book) {
		// TODO Auto-generated method stub
		bookRepository.save(book);
		return book;
	}

}
