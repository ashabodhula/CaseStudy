package com.bookservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookservice.entity.Book;

public interface BookRepository  extends CrudRepository<Book,Integer>{

}
