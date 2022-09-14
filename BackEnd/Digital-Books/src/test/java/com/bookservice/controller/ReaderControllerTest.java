package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookservice.model.Author;
import com.bookservice.model.Book;
import com.bookservice.model.Category;
import com.bookservice.model.Reader;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.ReaderRepository;
import com.bookservice.service.BookService;

@ExtendWith(MockitoExtension.class)
class ReaderControllerTest {

	@InjectMocks
	ReaderController readerController;
	@Mock
	ReaderRepository readerRepository;
	@Mock
	BookRepository bookRepository;
	@Mock
	BookService bookService;
	@Mock
	BookController bookController;

	private Reader sampleReader() {
		Reader reader = new Reader();
		reader.setId(1);
		reader.setEmail("asha1@gmail.com");
		reader.setPassword("asha");
		reader.setUsername("asha1");

		return reader;
	}

	private Book sampleBook() {
		Book book = new Book();
		book.setId(1);
		book.setAuthor("asha");
		book.setAuthorid(1);
		book.setCategory(Category.FICTION);
		// book.setCategory("fiction");
		book.setChapters("12");

		book.setPrice(200);
		book.setPublished_date(null);
		book.setPublisher("vintage");
		book.setStatus(true);
		book.setTitle("kafkaontheshore");

		return book;

	}

	@Test
	void testRegisterReader() {
		Reader reader = sampleReader();
		when(readerRepository.existsByUsername(reader.getUsername())).thenReturn(true);
		assertEquals(readerController.registerReader(reader),
				ResponseEntity.badRequest().body(" Username is already taken!"));

		when(readerRepository.existsByUsername(reader.getUsername())).thenReturn(false);
		when(readerRepository.existsByEmail(reader.getEmail())).thenReturn(true);
		assertEquals(readerController.registerReader(reader),
				ResponseEntity.badRequest().body("Error: Email is already in use!"));

		when(readerRepository.existsByEmail(reader.getEmail())).thenReturn(false);
		;
		assertEquals(readerController.registerReader(reader), ResponseEntity.ok(" Reader SignUp success"));
	}

	@Test
	void testLoginUser() throws Exception {
		Reader reader = sampleReader();

		when(readerRepository.findByEmailAndPassword(reader.getEmail(), reader.getPassword()))
				.thenReturn(Optional.ofNullable(reader));

		assertEquals(readerController.loginReader(reader), ResponseEntity.ok(" reader Login success"));

		when(readerRepository.findByEmailAndPassword(reader.getEmail(), reader.getPassword()))
				.thenReturn(Optional.empty());

		assertEquals(readerController.loginReader(reader),
				ResponseEntity.badRequest().body("Error: Invalid Credential"));

	}

	@Test
	void testBuyBook() {
		Reader reader = sampleReader();
		Book book = new Book();
		when(bookRepository.existsById(book.getId())).thenReturn(true);

		//reader.setPaymentId("DBPID2020" + (int) (Math.random() * 10000));

		assertEquals(readerRepository.save(reader), ResponseEntity.ok("book purchased pid is" + reader.getPaymentId()));

		when(bookRepository.existsById(book.getId())).thenReturn(false);
		assertEquals(readerRepository.save(reader), ResponseEntity.badRequest().body("no book found to purchase"));
	}

	@Test
	void testGetAllBooks() {
		Book book = sampleBook();
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		books.add(book);
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(readerController.getAllBooks().size(), books.size());
	}

	@Test
	void testGetPurchasedBooks() {
		String emailid = "abc@gmail.com";
		Reader reader = sampleReader();
		reader.setMyBooks("1,");
		Book book = sampleBook();
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		when(readerRepository.findByEmail(emailid)).thenReturn(Optional.ofNullable(reader));
		when(bookRepository.findAllById(Arrays.asList(Integer.valueOf(1)))).thenReturn(books);
		assertEquals(readerController.getPurchasedBooks(emailid), ResponseEntity.ok(books));
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(
				readerController.searchBook(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()),
				new ResponseEntity<String>("NO Books Found", HttpStatus.NOT_FOUND));
		when(readerRepository.findByEmail(reader.getEmail())).thenReturn(Optional.empty());
		assertEquals(readerController.getPurchasedBooks(reader.getEmail()),
				ResponseEntity.badRequest().body("No books Purchased with this email"));

	}

	@Test
	void testSearchBookByEmptyFields() {
		Book book = sampleBook();
		List<Book> books = new ArrayList<Book>();
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(
				readerController.searchBook(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()),
				new ResponseEntity<String>("NO Books Found", HttpStatus.NOT_FOUND));
	}

	@Test
	void testSearchBookNonEmptyFields() {
		Book book = sampleBook();
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(
				readerController.searchBook(Optional.ofNullable("author"), Optional.ofNullable(Category.FICTION),
						Optional.of(Double.valueOf(500.00)), Optional.ofNullable("publisher")),
				new ResponseEntity<List<Book>>(books, HttpStatus.FOUND));
	}

}
