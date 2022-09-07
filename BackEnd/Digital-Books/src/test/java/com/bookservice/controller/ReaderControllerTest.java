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
	@Mock BookRepository bookRepository;
	@Mock BookService bookService;
	
	
	private Reader SampleReader(){
		Reader reader=new Reader();
		reader.setId(1);
		reader.setEmail("asha1@gmail.com");
		reader.setPassword("asha");
		reader.setUsername("asha1");
		
		return reader;
		
		
	}

	@Test
	void testBuyBook() {
//		Reader reader= SampleReader();
//		Book book =new Book();
//		when(bookRepository.existsById(book.getId())).thenReturn(true);
// 		
//   reader.setPaymentId("DBPID2020"+(int)(Math.random()*10000));
//             
//		assertEquals(readerRepository.save(reader),ResponseEntity.ok("book purchased pid is"+reader.getPaymentId()));
//		
//		when(bookRepository.existsById(book.getId())).thenReturn(false);
//		assertEquals(readerRepository.save(reader),ResponseEntity.badRequest().body("no book found to purchase"));
	}

	@Test
	void testRegisterReader() {
		Reader reader =SampleReader();
		when(readerRepository.existsByUsername(reader.getUsername())).thenReturn(true);
		assertEquals(readerController.registerReader(reader),ResponseEntity.badRequest().body(" Username is already taken!"));
		
		
	when(readerRepository.existsByUsername(reader.getUsername())).thenReturn(false);
	when(readerRepository.existsByEmail(reader.getEmail())).thenReturn(true);
	assertEquals(readerController.registerReader(reader),ResponseEntity.badRequest().body("Error: Email is already in use!"));

	when(readerRepository.existsByEmail(reader.getEmail())).thenReturn(false);;
	assertEquals(readerController.registerReader(reader),ResponseEntity.ok(" Reader SignUp success"));
		}
	

	@Test
	void testLoginUser() throws Exception {
		Reader reader = SampleReader();

		when( readerRepository.findByEmailAndPassword(reader.getEmail(),reader.getPassword())).thenReturn(Optional.ofNullable(reader));
		
		assertEquals(readerController.loginUser(reader), ResponseEntity.ok(" reader Login success"));
		
		when( readerRepository.findByEmailAndPassword(reader.getEmail(),reader.getPassword())).thenReturn(Optional.empty());
		
		assertEquals(readerController.loginUser(reader),ResponseEntity.badRequest().body("Error: Invalid Credential"));
		
	}

	@Test
	void testGetBook() {
		
	}

}
