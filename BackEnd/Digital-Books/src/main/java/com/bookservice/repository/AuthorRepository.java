package com.bookservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookservice.model.Author;
import com.bookservice.model.Book;


public interface AuthorRepository extends JpaRepository<Author , Integer> {


	Optional<Author> findByEmailAndPassword(String eEmail, String password);
    Author findByEmail(String email);
    
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String eMail);
	Boolean existsById(Long bookid);
	Optional<Author> findById(Long authorid);
	Book save(Book book);


}
