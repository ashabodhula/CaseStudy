package com.bookservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookservice.model.Reader;

public interface ReaderRepository  extends JpaRepository<Reader ,Integer>{

	Optional<Reader> findByUsernameAndMyBooks(String username,int book) ;

	Optional<Reader> findByEmailAndPassword(String eMail, String password);

	Optional<Reader> findByEmail(String email);
	
	Boolean existsByEmail(String eMail);

	Boolean existsByUsername(String username);

	

}
