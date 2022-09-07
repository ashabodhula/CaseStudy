package com.bookservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.bookservice.controller.AuthorController;
import com.bookservice.controller.BookController;
import com.bookservice.model.Book;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;

class BookServiceTest {

	@InjectMocks
	BookController bookController;
	
	@Mock BookRepository bookrepo;
	@Mock BookService bookService;
	
	private Book SampleBook() {
		Book book = new Book();
		book.setId(1);
		book.setAuthor("asha");
		book.setAuthorid(1);
		book.setCategory("fiction");
		book.setChapters("12");
		book.setImage("null");
		book.setPrice(200);
		book.setPublished_date("01-01-2020");
		book.setPublisher("vintage");
		book.setStatus(true);
		book.setTitle("kafkaontheshore");
		return book;
	}
	@Test
	void testAddBookBook() {
		
		Book book=SampleBook();
		when(bookService.addBook(book)).thenReturn(book);
		Book addedbook=bookrepo.save(book);
		
		assertEquals(addedbook.getId(),book.getId());
		assertEquals(addedbook.getAuthor(),book.getAuthor());
		assertEquals(addedbook.getAuthorid(),book.getAuthorid());
		assertEquals(addedbook.getCategory(),book.getCategory());
		assertEquals(addedbook.getChapters(),book.getChapters());
		assertEquals(addedbook.getImage(),book.getImage());
		assertEquals(addedbook.getPrice(),book.getPrice());
		assertEquals(addedbook.getPublished_date(),book.getPublished_date());
		assertEquals(addedbook.getTitle(),book.getTitle());
		assertEquals(addedbook.getPublisher(),book.getPublisher());
		assertEquals(addedbook.isStatus(),book.isStatus());
	}
	
	
	
	
	
	
	
	
//
//	@Test
//	void testGetBooks() {
//		
//	}

}
