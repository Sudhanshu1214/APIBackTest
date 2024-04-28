package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RegisterRequest;

public interface RegisterDao {

	public RegisterRequest save(RegisterRequest registerRequest);

	public void update(RegisterRequest registerRequest, Long id);

	public void delete(Long id);

	public RegisterRequest getById(long id);

	public List<RegisterRequest> getAll();
}
