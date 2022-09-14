package com.bookservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Author;
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
public class ReaderController {
	@Autowired
	ReaderService readerService;
	@Autowired
	ReaderRepository readerRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired

	BookService bookService;

	@PostMapping(path = "/reader/signup")
	public ResponseEntity registerReader(@RequestBody Reader reader) {
//		System.out.println("Registering... User ID : " + user.getUserName());

		if (readerRepository.existsByUsername(reader.getUsername())) {
			return ResponseEntity.badRequest().body(" Username is already taken!");
		}

		if (readerRepository.existsByEmail(reader.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}

		readerRepository.save(reader);
		return ResponseEntity.ok(" Reader SignUp success");

	}

	@PostMapping("/reader/login")
	public ResponseEntity<?> loginReader(@RequestBody Reader reader) throws Exception {
		String tempEmailId = reader.getEmail();
		String tempPassword = reader.getPassword();

		Optional<Reader> readerObj = readerRepository.findByEmailAndPassword(tempEmailId, tempPassword);
		if (readerObj.isPresent()) {
			return ResponseEntity.ok(" reader Login success");
		}

		return ResponseEntity.badRequest().body("Error: Invalid Credential");
	}

	@GetMapping("/allbooks")
	List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@PostMapping("/books/buy/{id}")

	public ResponseEntity buyBook(@RequestBody Reader reader, @PathVariable("id") int bookid) {

		if (bookRepository.existsById(bookid)) {

			//String pid = "DBPID2020" + (int) (Math.random() * 10000);

//			reader.setPaymentId(pid);
            reader.setMyBooks("bookid"+bookid);
            reader.setPaymentId((int) (Math.random() * 10000));
			readerRepository.save(reader);

			return ResponseEntity.ok("book purchased pid is:" +reader.getPaymentId());
		}
		return ResponseEntity.badRequest().body("no book found to purchase");

	}

	@GetMapping("/readers/{emailid}/books")
	ResponseEntity<?> getPurchasedBooks(@PathVariable("emailid") String emailid) {
		Optional<Reader> reader = readerRepository.findByEmail(emailid);
		if (reader.isPresent()) {
			List<Integer> bookids = new ArrayList<>();
			String books = reader.get().getMyBooks();
			if (!books.equals("")) {
				for (String bookid : books.split(",")) {
					bookids.add(Integer.parseInt(bookid));
				}
				return ResponseEntity.ok(bookRepository.findAllById(bookids));
			} else {
				return ResponseEntity.badRequest().body("No Books Purchased");
			}
		}
		return ResponseEntity.badRequest().body("No books Purchased with this email");
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
