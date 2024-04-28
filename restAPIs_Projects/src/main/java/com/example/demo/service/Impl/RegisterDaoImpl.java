package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RegisterRequest;
import com.example.demo.repo.RegisterRepository;
import com.example.demo.service.RegisterDao;

@Service
public class RegisterDaoImpl implements RegisterDao{

	@Autowired
	private RegisterRepository registerRepository;
	
	@Override
	public RegisterRequest save(RegisterRequest registerRequest) {
		RegisterRequest registerRequest2 = registerRepository.save(registerRequest);
		System.out.println("Saved Recoed is : " + registerRequest2);
		return registerRequest2;
	}

	@Override
	public void update(RegisterRequest registerRequest, Long id) {
		registerRequest.setId(id);
		RegisterRequest registerRequest2 = registerRepository.save(registerRequest);
		System.out.println("Updated record : " + registerRequest2);
		
	}

	@Override
	public void delete(Long id) {
		registerRepository.deleteById(id);
		
	}

	@Override
	public RegisterRequest getById(long id) {
		RegisterRequest registerRequest = registerRepository.findById(id).get();
		return registerRequest;
	}

	@Override
	public List<RegisterRequest> getAll() {
		List<RegisterRequest> registerList = new ArrayList<RegisterRequest>();
		registerList = registerRepository.findAll();
		return registerList;
	}

}
