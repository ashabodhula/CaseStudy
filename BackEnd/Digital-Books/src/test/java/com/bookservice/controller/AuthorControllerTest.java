package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
	AuthorService authorService;

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

	private Author sampleAuthor() {
		Author author = new Author();
		author.setId(1);
		author.setEmail("asha1@gmail.com");
		author.setPassword("asha");
		author.setUsername("asha1");

		return author;

	}

	@Test
	void testRegisterReader() {
		Author author = sampleAuthor();
		when(authorRepository.existsByUsername(author.getUsername())).thenReturn(true);
		assertEquals(authorController.registerAuthor(author),
				ResponseEntity.badRequest().body(" Username is already taken!"));

		when(authorRepository.existsByUsername(author.getUsername())).thenReturn(false);
		when(authorRepository.existsByEmail(author.getEmail())).thenReturn(true);
		assertEquals(authorController.registerAuthor(author),
				ResponseEntity.badRequest().body("Error: Email is already in use!"));

		when(authorRepository.existsByEmail(author.getEmail())).thenReturn(false);
		
		assertEquals(authorController.registerAuthor(author), ResponseEntity.ok(" Author SignUp success"));
	}

	@Test
	void testLoginAuthor() throws Exception {
		Author author = sampleAuthor();

		when(authorRepository.findByEmailAndPassword(author.getEmail(), author.getPassword()))
				.thenReturn(Optional.ofNullable(author));

		assertEquals(authorController.loginAuthor(author), ResponseEntity.ok(" author Login success"));

		when(authorRepository.findByEmailAndPassword(author.getEmail(), author.getPassword()))
				.thenReturn(Optional.empty());

		assertEquals(authorController.loginAuthor(author),
				ResponseEntity.badRequest().body("Error: Invalid Credential"));

	}

	@Test
	void testGetBooksByAuthorId() {
		Book book = sampleBook();
		Author author = sampleAuthor();
		List<Book> books = new ArrayList<Book>();

		books.add(book);
		books.add(book);
		when(bookRepository.findAllByAuthorid(author.getId())).thenReturn(books) ;
		assertEquals(authorController.getBooksByAuthorId(author.getId()).size(),books.size());
	}
	
	@Test
	void testCreateBook() {
		Author author = sampleAuthor();
		Book book = sampleBook();

		when(authorService.createBook(book, author.getId())).thenReturn(book);

		Book addedbook = authorController.createBook(book, author.getId());
		assertEquals(addedbook.getId(), book.getId());
		assertEquals(addedbook.getAuthor(), book.getAuthor());
		assertEquals(addedbook.getAuthorid(), book.getAuthorid());
		assertEquals(addedbook.getCategory(), book.getCategory());
		assertEquals(addedbook.getChapters(), book.getChapters());
        assertEquals(addedbook.getPrice(), book.getPrice());
		assertEquals(addedbook.getPublished_date(), book.getPublished_date());
		assertEquals(addedbook.getTitle(), book.getTitle());
		assertEquals(addedbook.getPublisher(), book.getPublisher());
		assertEquals(addedbook.isStatus(), book.isStatus());

	}
	
	//test this method based on each case 
	
	@Test
	//success case
	void testUpdateBook() {
		Author author = sampleAuthor();
		Book book = sampleBook();
		
		author.setLoginstatus(true);
		when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		assertEquals(authorController.updateBook(book, author.getId(), book.getId()),ResponseEntity.ok("Your book has been updated Successfull"));
		
		
	}
	//when author is not logged in
	
	@Test
	 void testUpdateBookFailureCase() {
		Author author = sampleAuthor();
		Book book = sampleBook();
		
		author.setLoginstatus(false);
		when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		assertEquals(authorController.updateBook(book, author.getId(), book.getId()), ResponseEntity.badRequest().body("If you are an  author ,Please Login "));
		
		}
	
	@Test
	 void testUpdateBookCase2() {
		Author author = sampleAuthor();
		Book book = sampleBook();
		
		when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
		assertEquals(authorController.createBook(book, author.getId()),  ResponseEntity.badRequest().body("No Author Found"));
	}

	

	//when  book not exists by given id
	@Test
	 void testUpdateBookFailureCase3() {
		Author author = sampleAuthor();
		Book book = sampleBook();
		
		when(bookRepository.existsById(book.getId())).thenReturn(false);
		assertEquals(authorController.updateBook(book, author.getId(), book.getId()),new ResponseEntity<String>("No book found with given bookid",HttpStatus.UNAUTHORIZED));
	}
}
