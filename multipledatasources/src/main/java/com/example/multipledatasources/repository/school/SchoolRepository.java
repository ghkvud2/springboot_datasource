package com.example.multipledatasources.repository.school;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multipledatasources.entity.school.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
