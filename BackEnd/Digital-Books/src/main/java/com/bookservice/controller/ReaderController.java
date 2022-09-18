package com.bookservice.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Book;
import com.bookservice.model.Category;

import com.bookservice.model.Reader;
import com.bookservice.repository.BookRepository;

import com.bookservice.repository.ReaderRepository;
import com.bookservice.service.BookService;
import com.bookservice.service.ReaderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/digitalbooks")
public class ReaderController extends BaseController {
	@Autowired
	ReaderService readerService;
	@Autowired
	ReaderRepository readerRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired

	BookService bookService;

	@PostMapping("/books/buy/{id}")

	public ResponseEntity buyBook(@RequestBody Reader reader, @PathVariable("id") int bookid) {

		if (bookRepository.existsById(bookid)) {

			Optional<Reader> reader1 = readerRepository.findByUsernameAndBooks(reader.getUsername(), bookid);
			if (reader1.isPresent()) {

				return ResponseEntity.badRequest().body("Book already purchased");
			} else {
				int payId = (int) (Math.random() * 10000);
				reader.setBooks(bookid);
				reader.setPaymentid(payId);
				readerRepository.save(reader);
				return ResponseEntity.ok("Book purchased successfully, your paymentId is:" + payId);
			}

		}
		return ResponseEntity.badRequest().body("No book found to purchase");

	}
	
//	@PostMapping("/readers/{emailid}/books")
//	ResponseEntity<?> getBookByPaymentId(@PathVariable("emailid") String emailid,@RequestParam(required=true) int paymentid){
//		Optional<Reader> reader =readerRepository.findByEmailAndPaymentid(emailid, paymentid);
//		if (reader.isPresent()) {
//			return  ResponseEntity.ok(bookRepository.findById(reader.get().getBooks()));
//		}
//		return ResponseEntity.badRequest().body("Invalid Email / Payment ID");
//	}
//	
//	
	@PostMapping(path = "/reader/signup")
	public ResponseEntity registerReader(@RequestBody Reader reader) {
//		System.out.println("Registering... User ID : " + user.getUserName());

		if (readerRepository.existsByUsername(reader.getUsername())) {
			return ResponseEntity.badRequest().body(" Invalid Username");
		}

		if (readerRepository.existsByEmail(reader.getEmail())) {
			return ResponseEntity.badRequest().body("Invalid Email");
		}
		String encodedPassword = 
				  Base64.getEncoder().encodeToString(reader.getPassword().getBytes());
		reader.setPassword(encodedPassword);
       reader.setLoginstatus(true);
		readerRepository.save(reader);
		return ResponseEntity.ok(" Reader SignUp success"+reader.getId());

	}

	@PostMapping("/reader/login")
	public ResponseEntity<?> loginReader(@RequestBody Reader reader) throws Exception {
		Optional<Reader> reader1 = readerRepository.findByUsername(reader.getUsername());
		System.out.println(reader1.isPresent() + "testing");
		if (reader1.isPresent() && reader1.get().getPassword()
				.equals(Base64.getEncoder().encodeToString(reader.getPassword().getBytes()))) {
			reader1.get().setLoginstatus(true);
			readerRepository.save(reader1.get());
			return ResponseEntity.ok("Reader Login Success" + reader1.get().getId());
		}
		return ResponseEntity.badRequest().body("Invalid Credential");
	}

	@GetMapping("/allbooks")
	List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	
	
	
	@PostMapping("/readers/{emailid}/books/refund")
	ResponseEntity<?> getRefundBookByBookId(@PathVariable("emailid") String emailid,@RequestParam int bookid){
		Optional<Reader> reader =readerRepository.findByEmailAndBooks(emailid,bookid);
		if (reader.isPresent()) {
			readerRepository.delete(reader.get());
			return ResponseEntity.ok("Your refund will be credited shortly");
		}
		else {			
			return new ResponseEntity<>("Invalid Email / BookId",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/readers/{emailid}/books/{bookid}")
	ResponseEntity<?> readBookContent(@PathVariable("emailid") String emailid,@PathVariable("bookid") int bookid){
		Optional<Reader> reader =readerRepository.findByEmailAndBooks(emailid, bookid);
		if (reader.isPresent()) {
			return  ResponseEntity.ok(bookRepository.findById(reader.get().getBooks()));
		}
		return ResponseEntity.badRequest().body("Invalid Email / Book");
	}
	// reader should search books

	@GetMapping("/books/search")
	ResponseEntity<?> searchBook(@RequestParam Optional<String> author, @RequestParam Optional<Category> category,
			@RequestParam Optional<Double> price, @RequestParam Optional<String> publisher) {
		Stream<Book> stream = bookRepository.findAll().stream();
		if (author.isPresent()) {
			stream = stream.filter(book -> book.getAuthor().equals(author.get()));
		}
		if (category.isPresent()) {
			stream = stream.filter(book -> book.getCategory().equals(category.get()));
		}
		if (price.isPresent()) {
			stream = stream.filter(book -> String.valueOf(book.getPrice()).equals(String.valueOf(price.get())));
		}
		if (publisher.isPresent()) {
			stream = stream.filter(book -> book.getPublisher().equalsIgnoreCase(publisher.get()));
		}
		List<Book> searchResult = stream.collect(Collectors.toList());
		if (!searchResult.isEmpty()) {
			return new ResponseEntity<>(searchResult, HttpStatus.FOUND);
		}
		return new ResponseEntity<>("NO Books Found", HttpStatus.NOT_FOUND);
	}

	
}
