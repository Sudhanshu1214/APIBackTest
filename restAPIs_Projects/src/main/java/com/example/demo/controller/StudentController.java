package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.Constant;
import com.example.demo.model.Student;
import com.example.demo.send.email.EmailService;
import com.example.demo.service.StudentDao;

@RestController
@RequestMapping("api/student")
public class StudentController {

	private static final Logger logger = Logger.getLogger(StudentController.class.getName());

	private final Environment env;

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
    private EmailService emailService;

	public StudentController(Environment env) {
		this.env = env;
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> saveStudentRecord(@RequestBody Student student) {
		logger.info("StudentController:saveStudentRecord called!!");
		Map<String, Object> response = new HashMap<>();
		try {
			Student savedStudent = studentDao.save(student);

			if (savedStudent != null) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.ID, savedStudent.getId());
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

	@GetMapping(value = "/get")
	public ResponseEntity<Map<String, Object>> getStudentRecord(@RequestParam("id") Long id) {
		logger.info("StudentController:getStudentRecord called!!");
		Map<String, Object> response = new HashMap<>();
		try {
			if (id == null) {
				throw new Exception("request cannot be null!!!");
			}
			Student savedStudent = studentDao.getById(id);

			if (savedStudent != null) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.DATA, savedStudent);
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

	@PostMapping(value = "/upload")
	public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
		logger.info("StudentController:uploadFile called!!");
		Map<String, Object> response = new HashMap<>();
		String fullDestinationFilePath = null;
		String destinationFilePath = null;
		try {
			if (file.isEmpty()) {
				response.put(Constant.STATUS, "Failure");
				response.put(Constant.DATA, "file can not be empty");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			logger.info("env is : " + env);
			destinationFilePath = env.getProperty(Constant.DESTINATION_FILE_PATH);
			logger.info("destinationFilePath is : " + destinationFilePath);
			fullDestinationFilePath = destinationFilePath + file.getOriginalFilename();
			File destPath = new File(fullDestinationFilePath);
			file.transferTo(destPath);
			if (destPath.exists()) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.DATA, "File upload succesfully");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

		} catch (Exception e) {
			response.put(Constant.STATUS, "Failure");
			response.put(Constant.DATA, null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return null;
	}

	@PostMapping(value = "/delete")
	public ResponseEntity<Map<String, Object>> deleteStudentRecord(@RequestParam("id") Long id) {
		logger.info("StudentController:deleteStudentRecord called!!");
		Boolean flag = false;
		Map<String, Object> response = new HashMap<>();
		try {
			if (id != null) {
				studentDao.delete(id);
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
	public ResponseEntity<Map<String, Object>> updateStudentRecord(@RequestBody Student student,
			@RequestParam("id") Long id) {
		logger.info("StudentController:updateStudentRecord called!!");
		Map<String, Object> response = new HashMap<>();
		Boolean flag = false;
		try {
			if (id != null) {
				studentDao.update(student, id);
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
	public ResponseEntity<Map<String, Object>> getAllStudentRecord() {
		logger.info("StudentController:getAllStudentRecord called!!");
		Map<String, Object> response = new HashMap<>();
		try {

			List<Student> studentList = studentDao.getAll();

			if (!studentList.isEmpty()) {
				response.put(Constant.STATUS, "Success");
				response.put(Constant.DATA, studentList);
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
	
	@GetMapping(value = "/sendEmail")
	public ResponseEntity<Map<String, Object>> sendEmail() {
		logger.info("StudentController:sendEmail called!!");
		Map<String, Object> response = new HashMap<>();
		try {
			 String emailTo = env.getProperty(Constant.EMAIL_CONSTANTS.EMAIL_TO);
			 String subject = env.getProperty(Constant.EMAIL_CONSTANTS.EMAIL_SUBJECT);
			 String text = env.getProperty(Constant.EMAIL_CONSTANTS.EMAIL_MESSEGE);
			 emailService.sendSimpleMessage(emailTo, subject, text);

//			if (savedStudent != null) {
//				response.put(Constant.STATUS, "Success");
//				response.put(Constant.ID, savedStudent.getId());
//				return ResponseEntity.ok(response);
//			}
//			 else {
//				response.put(Constant.STATUS, "Failure");
//				response.put(Constant.DATA, null);
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//			}
		} catch (Exception e) {
			response.put(Constant.STATUS, "Failure");
			response.put(Constant.DATA, null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return null;
	}
}
