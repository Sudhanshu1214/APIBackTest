package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;

//@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
