package com.bookservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookservice.model.Book;
@Repository
public interface BookRepository extends JpaRepository <Book,Integer>{

	List<Book> findAllByAuthorid(int authorid);


	
	
}


