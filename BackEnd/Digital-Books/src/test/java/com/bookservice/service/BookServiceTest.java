package com.bookservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.bookservice.model.Book;
import com.bookservice.model.Category;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;

class BookServiceTest {
	@InjectMocks
    BookService bookService;

	@Mock
	BookRepository bookRepository;
	
	private Book SampleBook() {
		Book book = new Book();
		book.setId(1);
	
		book.setAuthor("asha");
		book.setAuthorid(1);
		book.setCategory(Category.FICTION);
		//book.setCategory("fiction");
		book.setChapters(12);
		//book.setImage("null");
		book.setPrice(200.00);
		book.setPublisheddate(null);
		book.setPublisher("vintage");
		book.setBookstatus(true);
		book.setTitle("kafkaontheshore");
		return book;
	}

	
	}


