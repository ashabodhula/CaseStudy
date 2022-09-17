package com.bookservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookservice.model.Payment;

@Repository


public interface PaymentRepository extends JpaRepository<Payment, Integer> {


}
