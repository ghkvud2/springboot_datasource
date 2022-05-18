package com.example.multipledatasources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multipledatasources.entity.school.School;
import com.example.multipledatasources.entity.student.Student;
import com.example.multipledatasources.repository.school.SchoolRepository;
import com.example.multipledatasources.repository.student.StudentRepository;

@RestController
public class MainController {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping(value = "school")
	public ResponseEntity<List<School>> getSchool() {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
			.body(schoolRepository.findAll());
	}

	@GetMapping(value = "student")
	public ResponseEntity<List<Student>> getStudent() {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
			.body(studentRepository.findAll());
	}
}
