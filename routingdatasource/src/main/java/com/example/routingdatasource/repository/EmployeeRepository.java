package com.example.routingdatasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.routingdatasource.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
