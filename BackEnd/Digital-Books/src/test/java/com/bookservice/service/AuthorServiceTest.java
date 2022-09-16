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
import com.bookservice.model.Category;
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
		book.setCategory(Category.FICTION);
		// book.setCategory("fiction");
		book.setChapters(12);

		book.setPrice(200.00);
		book.setPublisheddate(null);
		book.setPublisher("vintage");
		book.setBookstatus(true);
		book.setTitle("kafkaontheshore");
		return book;
	}

	private Author SampleAuthor() {
		Author author = new Author();
		author.setId(1);
		author.setEmail("asha1@gmail.com");
		author.setPassword("asha");
		author.setUsername("asha1");

		return author;
	}

	@Test
	void testSaveAuthor() {
		Author author = SampleAuthor();
		when(authorRepository.save(author)).thenReturn(author);
		authorService.saveAuthor(author);
		Author addedauthor = authorRepository.save(author);
		assertEquals(addedauthor.getId(), author.getId());
		assertEquals(addedauthor.getEmail(), author.getEmail());
		assertEquals(addedauthor.getPassword(), author.getPassword());
		assertEquals(addedauthor.getPassword(), author.getPassword());
		assertEquals(addedauthor.getClass(), author.getClass());
		assertEquals(addedauthor.toString(), author.toString());

	}

	@Test
	void testCreateBook() {
		Book book = SampleBook();
		Author author = SampleAuthor();
		when(authorService.createBook(book, author.getId())).thenReturn(book);
		Book addedbook = authorRepository.save(book);

		assertEquals(addedbook.getId(), book.getId());
		assertEquals(addedbook.getAuthor(), book.getAuthor());
		assertEquals(addedbook.getAuthorid(), book.getAuthorid());
		assertEquals(addedbook.getCategory(), book.getCategory());
		assertEquals(addedbook.getChapters(), book.getChapters());

		assertEquals(addedbook.getPrice(), book.getPrice());
		assertEquals(addedbook.getPublisheddate(), book.getPublisheddate());
		assertEquals(addedbook.getTitle(), book.getTitle());
		assertEquals(addedbook.getPublisher(), book.getPublisher());
		assertEquals(addedbook.isBookstatus(), book.isBookstatus());

	}

}
