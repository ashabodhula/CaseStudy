package com.bookservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookservice.model.Book;

public interface BookRepository extends JpaRepository <Book,Integer>{


	@Transactional

	@Modifying
	
	List<Book> findAll();
}


