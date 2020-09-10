package com.satyait.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyait.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}