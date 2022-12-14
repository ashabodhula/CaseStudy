package com.bookservice.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

import com.bookservice.model.Reader;
import com.bookservice.repository.AuthorRepository;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.ReaderRepository;
import com.bookservice.service.AuthorService;
import com.bookservice.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/digitalbooks")

public class AuthorController extends BaseController {

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;
	
	

	@PostMapping(path = "/author/signup")
	public ResponseEntity registerAuthor(@RequestBody Author author) {
		

		if (authorRepository.existsByUsername(author.getUsername())) {
			return ResponseEntity.badRequest().body(" Invalid Username");
		}

		if (authorRepository.existsByEmail(author.getEmail())) {
			return ResponseEntity.badRequest().body("Invalid Email");
		}
		String encodedPassword = 
				  Base64.getEncoder().encodeToString(author.getPassword().getBytes());
		author.setPassword(encodedPassword);
       author.setLoginstatus(true);
		authorRepository.save(author);
		return ResponseEntity.ok(" Author SignUp success"+author.getId());

	}

	@PostMapping("/author/login")
	public ResponseEntity<?> loginAuthor(@RequestBody Author author) throws Exception {
		Optional<Author> author1= authorRepository.findByUsername(author.getUsername());
		System.out.println(author1.isPresent()+"testing");
		if(author1.isPresent() && author1.get()
				.getPassword().equals( Base64.getEncoder().encodeToString(author.getPassword().getBytes()))) {
				author1.get().setLoginstatus(true);
				authorRepository.save(author1.get());
				return ResponseEntity.ok("Author Login Success"+author1.get().getId());
		}
	return  ResponseEntity.badRequest().body("Invalid Credential");
	}

	
	
	@PostMapping("/author/{authorid}/books")
	ResponseEntity<?> createBook(@Valid @RequestBody Book book, @PathVariable("authorid") int authorid) {
		Optional<Author> author = authorRepository.findById(authorid);
		if (author.isPresent()) {
			//add login status
			if (author.get().isLoginstatus()) {
				
				book.setAuthorid(authorid);
				
			
				book.setBookstatus(true);
				bookRepository.save(book);
				return ResponseEntity.ok("Book Created Successfully");
			} else {
				return ResponseEntity.badRequest().body("Please Login to Create Book");
			}
		}
		return new ResponseEntity<>("Unauthorised to create book", HttpStatus.UNAUTHORIZED);
	}
	//author edit the book
	
	@PutMapping("/author/{authorid}/books")
	ResponseEntity<?> updateBook(@Valid @RequestBody Book book, @PathVariable("authorid") int authorid,	@RequestParam int  bookid){
			
		Optional<Author> author = authorRepository.findById(authorid);
		if (bookRepository.existsById(bookid)) {
			if (author.isPresent()) {
				if (author.get().isLoginstatus()) {
					book.setAuthorid(authorid);
					book.setId(bookid);
					bookRepository.save(book);
					return ResponseEntity.ok("Your book has been updated Successfully");
				} else {
					return ResponseEntity.badRequest().body("If you are an  author ,Please Login");
				}
			} else {
				return ResponseEntity.badRequest().body("No Author Found");
			}
		}
		return new ResponseEntity<>("No book found with given bookid", HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/books/{authorid}")
	List<Book> getBooksByAuthorId(@PathVariable("authorid") int authorid) {
		return bookRepository.findAllByAuthorid(authorid);
	}

	
}
