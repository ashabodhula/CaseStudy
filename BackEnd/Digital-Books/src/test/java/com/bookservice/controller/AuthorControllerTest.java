package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bookservice.model.Author;
import com.bookservice.model.Book;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;
import com.bookservice.service.BookService;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {
	@InjectMocks
	AuthorController authorController;
	@Mock
	AuthorRepository authorRepository;
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
	private Author SampleAuthor(){
		Author author =new Author();
		author.setId(1);
		author.seteMail("asha1@gmail.com");
		author.setPassword("asha");
		author.setUsername("asha1");
		
		return author;
		
		
	}
	@Test
	void testAddBook() {
		Book book=SampleBook();
		when(bookService.addBook(book)).thenReturn(book);
		Book addedbook=authorController.addBook(book);
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

	@Test
	void testRegisterReader() {
			Author author =SampleAuthor();
	when(authorRepository.existsByUsername(author.getUsername())).thenReturn(true);
	assertEquals(authorController.registerReader(author),ResponseEntity.badRequest().body(" Username is already taken!"));
	
	
when(authorRepository.existsByUsername(author.getUsername())).thenReturn(false);
when(authorRepository.existsByEmail(author.geteMail())).thenReturn(true);
assertEquals(authorController.registerReader(author),ResponseEntity.badRequest().body("Error: Email is already in use!"));

when(authorRepository.existsByEmail(author.geteMail())).thenReturn(false);;
assertEquals(authorController.registerReader(author),ResponseEntity.ok(" Author SignUp success"));
	}

	@Test
	void testLoginUser() throws Exception {
		Author author = SampleAuthor();

		when( authorRepository.findByEmailAndPassword(author.geteMail(),author.getPassword())).thenReturn(Optional.ofNullable(author));
		
		assertEquals(authorController.loginUser(author), ResponseEntity.ok(" author Login success"));
		
		when( authorRepository.findByEmailAndPassword(author.geteMail(),author.getPassword())).thenReturn(Optional.empty());
		
		assertEquals(authorController.loginUser(author),ResponseEntity.badRequest().body("Error: Invalid Credential"));
		
	}

	@Test
	void testEditBook() {
		Author author = SampleAuthor();
		
		
//	
//when(author.isAuthorStatus()).thenReturn(true);
//	assertEquals(authorRepository.findById(author.getId()),ResponseEntity.ok("You have  Updated  the book Successfully"));
//		
//
//	when(author.isAuthorStatus()).thenReturn(false);
//assertEquals(authorRepository.findById(author.getId()),ResponseEntity.badRequest().body("Unable to edit ,please login with author credentials"));
//
//assertEquals(authorRepository.findById(author.getId()),ResponseEntity.badRequest().body("Unable to edit ,please login with author credentials"));


	}

}
