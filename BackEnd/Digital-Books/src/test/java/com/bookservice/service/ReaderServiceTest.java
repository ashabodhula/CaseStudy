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
import com.bookservice.model.Reader;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.ReaderRepository;
@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {
	@InjectMocks
	ReaderService readerService;

	@Mock
	ReaderRepository readerRepository;

	@Mock
	BookService bookService;


	private Reader SampleReader(){
		Reader reader=new Reader();
		reader.setId(1);
		reader.setEmail("asha1@gmail.com");
		reader.setPassword("asha");
		reader.setUsername("asha1");
		//reader.setPaymentId("DBPID20221234");
		
		return reader;
	}

	@Test
	void testSaveReader() {
		Reader reader = SampleReader();
		when(readerRepository.save(reader)).thenReturn(reader);
		readerService.saveReader(reader);
		Reader addedreader=readerRepository.save(reader);
		assertEquals(addedreader.getId(),reader.getId());
		assertEquals(addedreader.getEmail(),reader.getEmail());
		assertEquals(addedreader.getPassword(),reader.getPassword());
		assertEquals(addedreader.getPassword(),reader.getPassword());
		assertEquals(addedreader.getClass(),reader.getClass());
		assertEquals(addedreader.getPaymentId(),reader.getPaymentId());
		assertEquals(addedreader.toString(),reader.toString());
	}

}
