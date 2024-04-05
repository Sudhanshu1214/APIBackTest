package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;
import com.example.demo.service.StudentDao;

@Service
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student save(Student student) {
		Student student2 = studentRepository.save(student);
		System.out.println("Record saved : " + student2);
		return student2;
	}

	@Override
	public void update(Student student, Long id) {
		student.setId(id);
		Student student2 = studentRepository.save(student);
		System.out.println("Updated record : " + student2);
	}

	@Override
	public void delete(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student getById(long id) {
		Student student = studentRepository.findById(id).get();
		return student;
	}

	@Override
	public List<Student> getAll() {
		List<Student> studentList = new ArrayList<Student>();
		studentList = studentRepository.findAll();
		return studentList;
	}

}
