package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.model.Book;
import com.bookservice.repository.BookRepository;
import com.bookservice.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

	@InjectMocks
	BookService bookService;

	@Mock
	BookController bookController;

	@Test
	void testAddBook() {
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

		when(bookController.addBook(book)).thenReturn(book);// mock
		bookController.addBook(book);
		Book addedbook=bookController.addBook(book);
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

}
