package com.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Reader;
import com.bookservice.repository.ReaderRepository;
import com.bookservice.service.ReaderService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/digitalbooks")
public class ReaderController
{
	@Autowired
	ReaderService readerservice;
	@Autowired
	ReaderRepository readerRepo;



	@RequestMapping(path="/reader/signup",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity registerReader(@RequestBody Reader reader)
	{
//		System.out.println("Registering... User ID : " + user.getUserName());
		
	Reader readerObj;
	ResponseEntity<Reader> response ;
	Reader existReader =readerRepo.findByEmail(reader.geteMail());
	if(reader.geteMail().equals(existReader.geteMail())) {
		MultiValueMap headers = new LinkedMultiValueMap<String, String>();
		headers.add("headerfromserver", "User Already Exists PLease login!");
		return new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
	}
	else {
		readerObj = this.readerservice.saveReader(reader);
		response =new ResponseEntity<>(readerObj,HttpStatus.CREATED);
	}
	return response;
		
	}


	@PostMapping("/login")
	public Reader loginUser(@RequestBody Reader reader) throws Exception
	{
		String tempEmailId = reader.geteMail();
		String tempPassword = reader.getPassword();

		
		Reader readerObj = null;
		
		if(tempEmailId !=null && tempPassword !=null)
		{
			readerObj = readerservice.fetchUserByEmailAndUserPassword(tempEmailId , tempPassword);	
		}
		if(readerObj == null) {
			throw new Exception("Bad Credentials");
		}
		
		return readerObj;
	}
}

	
	
	

	

