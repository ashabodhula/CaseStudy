package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Disabled;
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
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;
import com.bookservice.service.AuthorService;
import com.bookservice.service.BookService;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {
	@InjectMocks
	AuthorController authorController;
	@Mock
	AuthorRepository authorRepository;
	@Mock
	BookService bookService;
	@Mock
	BookRepository bookRepository ;
	@Mock
	BookController bookController;
	@Mock
	AuthorService authorService;

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

	private Author sampleAuthor() {
		Base64.Encoder encoder= Base64.getEncoder();
		Author author = new Author();
		author.setId(1);
		author.setEmail("asha1@gmail.com");
		//author.setPassword(encoder.encodeToString("abc".getBytes()));
		author.setUsername("asha1");
		author.setPassword("abc");

		return author;

	}

	@Test
	void testRegisterAuthor() {
		Author author = sampleAuthor();
		when(authorRepository.existsByUsername(author.getUsername())).thenReturn(true);
		assertEquals(authorController.registerAuthor(author),
				ResponseEntity.badRequest().body(" Invalid Username"));

		when(authorRepository.existsByUsername(author.getUsername())).thenReturn(false);
		when(authorRepository.existsByEmail(author.getEmail())).thenReturn(true);
		assertEquals(authorController.registerAuthor(author),
				 ResponseEntity.badRequest().body("Invalid Email"));

		when(authorRepository.existsByEmail(author.getEmail())).thenReturn(false);
		
		assertEquals(authorController.registerAuthor(author), ResponseEntity.ok(" Author SignUp success"+author.getId()));
	}

	@Test
	void testLoginAuthor() throws Exception {
		Author author = sampleAuthor();
		Author author1 = sampleAuthor();
 author1.setPassword(Base64.getEncoder().encodeToString("abc".getBytes()));
		when(authorRepository.findByUsername(author.getUsername()))
				.thenReturn(Optional.ofNullable(author1));

		assertEquals(authorController.loginAuthor(author), ResponseEntity.ok("Author Login Success"+author.getId()));

		when(authorRepository.findByUsername(author.getUsername()))
				.thenReturn(Optional.empty());

		assertEquals(authorController.loginAuthor(author),
				ResponseEntity.badRequest().body("Invalid Credential"));
		
		when(authorRepository.findByUsername(author.getUsername()))
		.thenReturn(Optional.ofNullable(author));
		assertEquals(authorController.loginAuthor(author),
				ResponseEntity.badRequest().body("Invalid Credential"));

	}
	
	

	@Test
	void testGetBooksByAuthorId() {
		Book book = sampleBook();
		Author author = sampleAuthor();
		List<Book> books = new ArrayList<Book>();

	
		books.add(book);
		when(bookRepository.findAllByAuthorid(author.getId())).thenReturn(books) ;
		assertEquals(authorController.getBooksByAuthorId(author.getId()).size(),books.size());
	}
	
	@Test
	void testCreateBook() {
		Author author = sampleAuthor();
		Book book =sampleBook();
		when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
		author.setLoginstatus(true);
		assertEquals(authorController.createBook(book, author.getId()),ResponseEntity.ok("Book Created Successfully"));
	}
	
	//fail cases
	@Test
	void testCreateBookNotLoggedIn() {
		Author author = sampleAuthor();
		Book book =sampleBook();
		author.setLoginstatus(false);
		when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
		assertEquals(authorController.createBook(book, author.getId()),ResponseEntity.badRequest().body("Please Login to Create Book"));
	}
	
	@Test
	void testCreateBooknoAuthor() {
		Author author = sampleAuthor();
		Book book =sampleBook();
		when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
		assertEquals(authorController.createBook(book, author.getId()),new  ResponseEntity<>("Unauthorised to create book", HttpStatus.UNAUTHORIZED));
	}
	//test this method based on each case 
	
	
	
	

	Author author = sampleAuthor();
	Book book =sampleBook();
		@Test
		void testUpdateBookSuccess() {
			author.setLoginstatus(true);
			when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
			when(bookRepository.existsById(book.getId())).thenReturn(true);
			assertEquals(authorController.updateBook(book, author.getId(), book.getId()),ResponseEntity.ok("Your book has been updated Successfully"));
		}
		
		@Test
		void testUpdateBookFailByNotLoggedIn() {
			author.setLoginstatus(false);
			when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
			when(bookRepository.existsById(book.getId())).thenReturn(true);
			assertEquals(authorController.updateBook(book, author.getId(), book.getId()),ResponseEntity.badRequest().body("If you are an  author ,Please Login"));
		}
		
		@Test
		void testUpdateBookFailByInvalidAuthor() {
			author.setLoginstatus(false);
			when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
			when(bookRepository.existsById(book.getId())).thenReturn(true);
			assertEquals(authorController.updateBook(book, author.getId(), book.getId()),ResponseEntity.badRequest().body("No Author Found"));
		}
		
		@Test
		void testUpdateBookFailByInvalidBook() {
			when(bookRepository.existsById(book.getId())).thenReturn(false);
			assertEquals(authorController.updateBook(book, author.getId(), book.getId()),new ResponseEntity<String>("No book found with given bookid",HttpStatus.UNAUTHORIZED));
		}
		

	}
	
	
	

