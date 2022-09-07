package com.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.model.Author;
import com.bookservice.model.Reader;
import com.bookservice.repository.ReaderRepository;


@Service
public class ReaderService {

	@Autowired
	ReaderRepository readerRepo;
	

	public Reader saveReader(Reader reader) {
		// TODO Auto-generated method stub
		readerRepo.save(reader);
		return reader;
	}

//	public Reader fetchUserByEmailAndUserPassword(String mail, String password) {
//		// TODO Auto-generated method stub
//		return readerRepo.findByEmailAndPassword(mail, password);
//
//	}
//
//	public Reader fetchUserByEmail(String email) {
//
//		return readerRepo.findByEmail(email);
//
//	}
}
