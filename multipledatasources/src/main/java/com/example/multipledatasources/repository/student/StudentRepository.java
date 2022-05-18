package com.example.multipledatasources.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multipledatasources.entity.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
