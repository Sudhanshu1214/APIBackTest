package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.Constant;
import com.example.demo.model.RegisterRequest;
import com.example.demo.service.RegisterDao;


@RestController
@RequestMapping("api/login")
@CrossOrigin( allowedHeaders ="*" ,origins="http://localhost:4200")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private RegisterDao registerDao;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> saveRecord(@RequestBody RegisterRequest registerRequest) {
		logger.info("LoginController:saveRecord called!!");
		Map<String, Object> response = new HashMap<>();
		try {
			RegisterRequest registerRequests = registerDao.save(registerRequest);

			if (registerRequests != null) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.ID, registerRequests);
				return ResponseEntity.ok(response);
			} else {
				response.put(Constant.STATUS, "Failure");
				response.put(Constant.DATA, null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		} catch (Exception e) {
			response.put(Constant.STATUS, "Failure");
			response.put(Constant.DATA, null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<Map<String, Object>> deleteRecord(@RequestParam("id") Long id) {
		logger.info("LoginController:deleteRecord called!!");
		Boolean flag = false;
		Map<String, Object> response = new HashMap<>();
		try {
			if (id != null) {
				registerDao.delete(id);
				flag = true;
			}
			if (flag) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.DATA, "Deleted record successfully for id : " + id);
				return ResponseEntity.ok(response);
			} else {
				response.put(Constant.STATUS, "Failure");
				response.put(Constant.DATA, null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		} catch (Exception e) {
			response.put(Constant.STATUS, "Failure");
			response.put(Constant.DATA, null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateRecord(@RequestBody RegisterRequest registerRequest,
			@RequestParam("id") Long id) {
		logger.info("LoginController:updateRecord called!!");
		Map<String, Object> response = new HashMap<>();
		Boolean flag = false;
		try {
			if (id != null) {
				registerDao.update(registerRequest, id);
				flag = true;
			}
			if (flag) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.MESSEGE, "Record updated succesfully for id : " + id);
				return ResponseEntity.ok(response);
			} else {
				response.put(Constant.STATUS, "Failure");
				response.put(Constant.DATA, null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		} catch (Exception e) {
			response.put(Constant.STATUS, "Failure");
			response.put(Constant.DATA, null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<Map<String, Object>> getAllRegistedRecord() {
		logger.info("LoginController:getAllRegistedRecord called!!");
		Map<String, Object> response = new HashMap<>();
		try {

			List<RegisterRequest> registeredList = registerDao.getAll();

			if (!registeredList.isEmpty()) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.DATA, registeredList);
				return ResponseEntity.ok(response);
			} else {
				response.put(Constant.STATUS, "Failure");
				response.put(Constant.DATA, null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
		} catch (Exception e) {

			response.put(Constant.STATUS, "Failure");
			response.put(Constant.DATA, null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
