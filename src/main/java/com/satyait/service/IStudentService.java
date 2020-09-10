package com.satyait.service;

import java.util.List;
import java.util.Optional;

import com.satyait.model.Student;

public interface IStudentService {

	public Integer saveStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(Integer id);
	public Optional<Student> getOneStudent(Integer id);
	public List<Student> getAllStudent();
	public boolean isStudentExist(Integer id);
}
