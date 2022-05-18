package com.example.routingdatasource.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.routingdatasource.entity.Employee;
import com.example.routingdatasource.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
}
