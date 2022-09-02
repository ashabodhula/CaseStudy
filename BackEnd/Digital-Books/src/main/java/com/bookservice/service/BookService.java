package com.bookservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.model.Book;
import com.bookservice.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookrepo;

	public List<Book> addBook(List<Book> books) {
		for (Book book : books) {
			bookrepo.save(book);
			System.out.println(book);
		}
		return  books;
	}

	
	public List<Book> getBookList() {
		
		List<Book> books = bookrepo.findAll();//here need to show books which are active
		
		 
		return books;
	}

}
