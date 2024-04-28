package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RegisterRequest;

public interface RegisterRepository extends JpaRepository<RegisterRequest, Long> {

}
