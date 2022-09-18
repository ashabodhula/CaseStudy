package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookservice.model.Book;
import com.bookservice.model.Category;
import com.bookservice.model.Reader;
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
		reader.setLoginstatus(true);
        reader.setPaymentid(1);
		return reader;
	}

	private Book sampleBook() {
		Book book = new Book();
		book.setId(1);
		book.setAuthor("asha");
		book.setAuthorid(1);
		book.setCategory(Category.FICTION);
		// book.setCategory("fiction");
		book.setChapters(12);
		book.setContent("this book");
		book.setPrice(200.00);
		book.setPublisheddate(null);
		book.setPublisher("vintage");
		book.setBookstatus(true);
		book.setTitle("kafkaontheshore");

		return book;

	}
	

	@Test
	void testRegisterReader() {
		Reader reader = sampleReader();
		when(readerRepository.existsByUsername(reader.getUsername())).thenReturn(true);
		assertEquals(readerController.registerReader(reader), ResponseEntity.badRequest().body(" Invalid Username"));

		when(readerRepository.existsByUsername(reader.getUsername())).thenReturn(false);
		when(readerRepository.existsByEmail(reader.getEmail())).thenReturn(true);
		assertEquals(readerController.registerReader(reader), ResponseEntity.badRequest().body("Invalid Email"));

		when(readerRepository.existsByEmail(reader.getEmail())).thenReturn(false);
		;
		assertEquals(readerController.registerReader(reader),
				ResponseEntity.ok(" Reader SignUp success" + reader.getId()));
	}

	@Test
	void testLoginReader() throws Exception {
		Reader reader = sampleReader();
		Reader reader1 = sampleReader();
		reader1.setPassword(Base64.getEncoder().encodeToString("asha".getBytes()));
		when(readerRepository.findByUsername(reader.getUsername())).thenReturn(Optional.ofNullable(reader1));

		assertEquals(readerController.loginReader(reader), ResponseEntity.ok("Reader Login Success" + reader.getId()));

		when(readerRepository.findByUsername(reader.getUsername())).thenReturn(Optional.empty());

		assertEquals(readerController.loginReader(reader), ResponseEntity.badRequest().body("Invalid Credential"));

		when(readerRepository.findByUsername(reader.getUsername())).thenReturn(Optional.ofNullable(reader));
		assertEquals(readerController.loginReader(reader), ResponseEntity.badRequest().body("Invalid Credential"));

	}

	
	
	
	@Test
	 void testBuyBook() {
		Book book = sampleBook();
		Reader reader = sampleReader();
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		when(readerRepository.findByUsernameAndBooks(reader.getUsername(), book.getId())).thenReturn(Optional.ofNullable(reader));
		assertEquals(readerController.buyBook(reader, book.getId()), ResponseEntity.badRequest().body("Book already purchased"));
		
		when(readerRepository.findByUsernameAndBooks(reader.getUsername(), book.getId())).thenReturn(Optional.empty());
		assertEquals(readerController.buyBook(reader, book.getId()),ResponseEntity.ok("Book purchased successfully, your paymentId is:" +reader.getPaymentid()));
		
		when(bookRepository.existsById(book.getId())).thenReturn(false);
//		when(readerRepository.findByUsernameAndBooks(reader.getUsername(), book.getId())).thenReturn(Optional.ofNullable(reader));
		assertEquals(readerController.buyBook(reader, book.getId()),ResponseEntity.badRequest().body("No book found to purchase"));
		
		
	}
	
	@Test
	void testReadBookContent() {
		Book book = sampleBook();
		Reader reader = sampleReader();
		when(readerRepository.findByEmailAndBooks(reader.getEmail(), reader.getBooks())).thenReturn(Optional.empty());
		assertEquals(readerController.readBookContent(reader.getEmail(), reader.getBooks()),ResponseEntity.badRequest().body("Invalid Email / Book"));
		
		when(readerRepository.findByEmailAndBooks(reader.getEmail(), reader.getBooks())).thenReturn(Optional.ofNullable(reader));
		when(bookRepository.findById(reader.getBooks())).thenReturn(Optional.ofNullable(book));
		assertEquals(readerController.readBookContent(reader.getEmail(),reader.getBooks()),ResponseEntity.ok(Optional.ofNullable(book)));
	}
	
	@Test
	void testGetRefundByBookId() {
		Book book = sampleBook();
		Reader reader = sampleReader();
		when(readerRepository.findByEmailAndBooks(reader.getEmail(),reader.getBooks())).thenReturn(Optional.ofNullable(reader));
		assertEquals(readerController.getRefundBookByBookId(reader.getEmail(), reader.getBooks()),new ResponseEntity<String>("Your refund will be credited shortly",HttpStatus.OK));
		
		when(readerRepository.findByEmailAndBooks(reader.getEmail(),reader.getBooks())).thenReturn(Optional.empty());
		assertEquals(readerController.getRefundBookByBookId(reader.getEmail(),reader.getBooks()),new ResponseEntity<>("Invalid Email / BookId",HttpStatus.NOT_FOUND));
	}
	

	
	@Test
	void testGetAllBooks() {
		Book book = sampleBook();
		List<Book> books = new ArrayList<Book>();
		
		books.add(book);
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(readerController.getAllBooks().size(), books.size());
	}

	
	
	@Test
	void testSearchBookByEmptyFields() {
		
		List<Book> books = new ArrayList<Book>();
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(readerController.searchBook( Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()),
				new ResponseEntity<String>("NO Books Found",HttpStatus.NOT_FOUND));
	}
	
	@Test
	void testSearchBookNonEmptyFields() {
		List<Book> books = new ArrayList<Book>();
		Book book = sampleBook();
		books.add(book);
		when(bookRepository.findAll()).thenReturn(books);
		assertEquals(readerController.searchBook( Optional.ofNullable("asha"),Optional.ofNullable(Category.FICTION),Optional.of(Double.valueOf(200.00)),Optional.ofNullable("vintage")),
				new ResponseEntity<List<Book>>(books, HttpStatus.FOUND));
	}

}
