package com.bookservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookservice.model.Reader;

public interface ReaderRepository  extends JpaRepository<Reader ,Integer>{

	Optional<Reader> findByUsernameAndBooks(String username,int bookid) ;

	Optional<Reader> findByEmailAndPassword(String eMail, String password);

	Optional<Reader> findByEmail(String email);
	
	Boolean existsByEmail(String eMail);

	Boolean existsByUsername(String username);

	Optional<Reader> findByUsername(String username);

	Optional<Reader> findByPaymentid(int paymentid);

	

	

	Optional<Reader> findByEmailAndPaymentid(String emailid, int paymentid);

	Optional<Reader> findByEmailAndBooks(String emailid, int bookid);

	//Optional<Reader> findAllByReaderemail(String emailid);

	
	

}
