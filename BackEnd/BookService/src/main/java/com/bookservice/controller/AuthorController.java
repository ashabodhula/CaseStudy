package com.bookservice.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.Author;

import com.bookservice.repository.AuthorRepository;

import com.bookservice.service.AuthorService;

@RestController
@RequestMapping("/api/")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	@Autowired
	AuthorRepository authorRepository;
	
	

	@GetMapping
	Iterable<Author> getBooks() {
		return authorService.getAuthor();
	}

	@PostMapping // author can create book
	Author saveAuthor(@Valid @RequestBody Author author) {

		authorService.saveAuthor(author);
		return author;
	}

	@PostMapping("/authorname/{authorname}/bookId/{bookId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	ResponseEntity saveUser(@Valid @RequestBody Author author, @PathVariable("authorname") float authorname,
			@PathVariable("bookId") int bookId) {
		authorService.saveAuthor(author);

		MultiValueMap headers = new LinkedMultiValueMap<String, String>();
		headers.add("headerfromserver", "success");
		ResponseEntity responseEntity = new ResponseEntity(author, headers, HttpStatus.CREATED);
		return responseEntity;

	}
/*
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (authorRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse(" Email is already in use!"));
		}

		// Create new account
		
		Author author = new Author(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		
		Set<String> strRoles = signUpRequest.getRole();

		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role authorRole = roleRepository.findByName(Role.ROLE_AUTHOR)
					.orElseThrow(() -> new RuntimeException("please check your role"));// if this role is not found
			roles.add(authorRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "reader":
					Role adminRole = roleRepository.findByName(Role.ROLE_READER)
							.orElseThrow(() -> new RuntimeException("please check your role."));
					roles.add(adminRole);

					break;
				case "admin":
					Role modRole = roleRepository.findByName(Role.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("please check your role."));
					roles.add(modRole);

					break;

				}
			});
		}

		
		authorRepository.save(author);

		return ResponseEntity.ok(new MessageResponse(" Registration successfull"));
		*/
	}

