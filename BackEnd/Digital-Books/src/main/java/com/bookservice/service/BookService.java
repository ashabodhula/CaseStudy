package com.bookservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookservice.model.Book;
import com.bookservice.model.Reader;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.ReaderRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	ReaderRepository readerRepo;

	


	



}
