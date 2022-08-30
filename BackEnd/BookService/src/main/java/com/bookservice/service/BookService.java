package com.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.entity.Book;
import com.bookservice.repository.BookRepository;
@Service
public class BookService {
	@Autowired
	
	BookRepository bookRepository;
	public Book saveBook(Book book) {
		
		bookRepository.save(book);
		return book;
		
	}
	public Iterable<Book> getBook() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

}
