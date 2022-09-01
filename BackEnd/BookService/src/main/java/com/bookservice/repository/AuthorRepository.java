package com.bookservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookservice.entity.Author;

public interface AuthorRepository extends CrudRepository <Author,Integer>{

}
