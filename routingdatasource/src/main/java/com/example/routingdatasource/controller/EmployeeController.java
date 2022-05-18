package com.example.routingdatasource.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.routingdatasource.entity.Employee;
import com.example.routingdatasource.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping(value = "employee")
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
			.body(employeeService.getEmployees());
	}
}
