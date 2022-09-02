package com.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookservice.model.Reader;

public interface ReaderRepository  extends JpaRepository<Reader ,Integer>{

	Reader findByEmailAndPassword(String eMail, String password);

	Reader findByEmail(String email);

	

}
