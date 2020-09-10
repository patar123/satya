package com.satyait.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyait.model.Student;
import com.satyait.repo.StudentRepository;
import com.satyait.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public Integer saveStudent(Student student) {
		Student save = studentRepo.save(student);
		return save.getStdId();
	}

	@Override
	public void updateStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void deleteStudent(Integer id) {
		studentRepo.deleteById(id);
	}

	@Override
	public Optional<Student> getOneStudent(Integer id) {
		return studentRepo.findById(id);
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> findAll = studentRepo.findAll();
		return findAll	;
	}

	@Override
	public boolean isStudentExist(Integer id) {
		return studentRepo.existsById(id);
	}
}
