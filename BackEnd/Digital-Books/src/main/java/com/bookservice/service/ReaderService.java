package com.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.model.Reader;
import com.bookservice.repository.BookRepository;
import com.bookservice.repository.ReaderRepository;


@Service
public class ReaderService {

	@Autowired
	ReaderRepository readerRepo;
	@Autowired
	BookRepository bookRepository;
	
	
	

	

	public Reader saveReader(Reader reader) {
		// TODO Auto-generated method stub
//		String encodedPassword =  passwordEncoder.encode(reader.getPassword());
//		reader.setPassword(encodedPassword);
		readerRepo.save(reader);
		return reader;
	}





	

//	public String readBook(String emailId, int readerId) {
//		// TODO Auto-generated method stub
//		Book book=new Book();
//		Reader reader = new Reader();
//		
//		if(reader.getPaymentId()!=null) {
//			
//				if(reader.getId()==readerId) {
//					Optional<Reader> bookBought=readerRepo.findById(readerId);
//					if(bookBought.isPresent()) {
//						reader=bookBought.get();
//					}
//				}
//			}
//		
//		return book.getChapters();
//	}

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
