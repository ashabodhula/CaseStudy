package com.bookservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.model.Author;
import com.bookservice.model.Book;
import com.bookservice.repository.AuthorRepository;


@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
	@InjectMocks
	AuthorService authorService;

	@Mock
	AuthorRepository authorRepository;

	@Mock
	BookService bookService;

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
	void testSaveAuthor() {
		Author author = SampleAuthor();
		when(authorRepository.save(author)).thenReturn(author);
		authorService.saveAuthor(author);
		Author addedauthor=authorRepository.save(author);
		assertEquals(addedauthor.getId(),author.getId());
		assertEquals(addedauthor.geteMail(),author.geteMail());
		assertEquals(addedauthor.getPassword(),author.getPassword());
		assertEquals(addedauthor.getPassword(),author.getPassword());
		assertEquals(addedauthor.getClass(),author.getClass());
		assertEquals(addedauthor.toString(),author.toString());
		
		

	}

	@Test
	void testSaveBook() {
		Book book=SampleBook();
		when(authorService.saveBook(book)).thenReturn(book);
		Book addedbook=authorRepository.save(book);
		
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
