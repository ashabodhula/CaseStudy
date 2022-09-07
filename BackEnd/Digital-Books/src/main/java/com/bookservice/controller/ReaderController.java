package com.bookservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.bookservice.model.Reader;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.ReaderRepository;
import com.bookservice.service.ReaderService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/digitalbooks")
public class ReaderController
{
	@Autowired
	ReaderService readerservice;
	@Autowired
	ReaderRepository readerRepository;
     @Autowired
     BookRepository bookRepository;
     
   
   
     
     
     
	@PostMapping(path="/reader/signup")
	public ResponseEntity registerReader(@RequestBody Reader reader)
	{
//		System.out.println("Registering... User ID : " + user.getUserName());
		if(reader.getEmail()==null) {
		return ResponseEntity.badRequest().body("Please enter valid email to signup");
		}
			

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
	public ResponseEntity<?> loginUser(@RequestBody Reader reader) throws Exception
	{
		String tempEmailId = reader.getEmail();
		String tempPassword = reader.getPassword();

		Optional<Reader> readerObj = readerRepository.findByEmailAndPassword(tempEmailId, tempPassword);
		if (readerObj.isPresent()) {
			return ResponseEntity.ok(" reader Login success");
		}

		return ResponseEntity.badRequest().body("Error: Invalid Credential");
	}
	
	  @PostMapping(path="/reader/buybook/{id}")
	     public ResponseEntity buyBook(@RequestBody Reader reader ,@PathVariable("id") int bookid ) {
	    	 
	    	if( bookRepository.existsById(bookid)) {
	    		
	    		
	   String pid="DBPID2020"+(int)(Math.random()*10000);
	             
	    		
	    	reader.setPaymentId(pid);
	    		

	    		readerRepository.save(reader);
	    		
	    		return ResponseEntity.ok("book purchased pid is:"+pid);
	    	}
			return ResponseEntity.badRequest().body("no book found to purchase");
	    	 
	     }
	@GetMapping("/searchhbooks")
		ResponseEntity<?> getBook( @RequestParam(required=false) Optional<String> author,
				@RequestParam(required=false) Optional<String> category,
				@RequestParam(required=false) Optional<Integer> price,
				@RequestParam(required=false) Optional<String> publisher) {
			Stream<Book> bookstream= bookRepository.findAll().stream();
			if(author.isPresent() ) {
				bookstream = bookstream.filter(book -> book.getAuthor().equals(author.get()));
			}
			if(category.isPresent()) {
				bookstream= bookstream.filter(book -> book.getCategory().equals(category.get()));
			}
			if(price.isPresent()) {
				bookstream=bookstream.filter(book ->book.getPrice()==price.get());
			}
			if(publisher.isPresent()) {
				bookstream=bookstream.filter(book -> book.getPublisher().equals(publisher.get()));
			}
			List<Book> searchResult=bookstream.collect(Collectors.toList());
			
			if(searchResult.size()>0) {
				return new ResponseEntity<List<Book>>(searchResult, HttpStatus.FOUND);
			}
				
			return new ResponseEntity<String>("NO Books Found",HttpStatus.NOT_FOUND);
			
		}
		
	}




	
	
	

	

