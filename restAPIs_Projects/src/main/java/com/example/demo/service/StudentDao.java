package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface StudentDao {

	public Student save(Student student);

	public void update(Student student, Long id);

	public void delete(Long id);

	public Student getById(long id);

	public List<Student> getAll();

}
