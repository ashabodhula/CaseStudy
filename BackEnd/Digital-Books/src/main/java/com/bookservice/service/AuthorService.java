package com.bookservice.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookservice.model.Author;
import com.bookservice.model.Book;

import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	public Author saveAuthor(Author author) {
		// TODO Auto-generated method stub
		authorRepository.save(author);
		return author;
	}

	public Book createBook(@Valid Book book, int authorId) {
		authorRepository.save(book);
		// TODO Auto-generated method stub
		return book;
	}

//	public List<Book> getBookList() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
