package com.jspiders.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.dto.Student;
import com.jspiders.springrest.response.StudentResponse;
import com.jspiders.springrest.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping(path = "/addStudent", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	private ResponseEntity<StudentResponse> insertStudent(@RequestBody Student student) {
		Student insertedStudent = service.insert(student);
		StudentResponse response = new StudentResponse();
		if (insertedStudent != null) {
			response.setMsg("OK");
			response.setDescription("Student inserted");
			response.setStudent(insertedStudent);
		} else {
			response.setMsg("FAIL");
			response.setDescription("Student not inserted");
		}
		return new ResponseEntity<StudentResponse>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "search{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	private ResponseEntity<StudentResponse> searchStudent(@PathVariable int id) {
		Student student = service.search(id);
		StudentResponse response = new StudentResponse();
		if (student != null) {
			response.setMsg("OK");
			response.setDescription("Student found");
			response.setStudent(student);
		} else {
			response.setMsg("FAIL");
			response.setDescription("Student not found");
		}
		return new ResponseEntity<StudentResponse>(response, HttpStatus.ACCEPTED);
	}

}