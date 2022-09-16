package com.bookservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Author;
import com.bookservice.model.Book;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;
import com.bookservice.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/digitalbooks")
public class BookController extends BaseController {

	


	
	

	
	

}
