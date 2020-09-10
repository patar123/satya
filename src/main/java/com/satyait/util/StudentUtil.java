package com.satyait.util;

import org.springframework.stereotype.Component;

import com.satyait.model.Student;


@Component
public class StudentUtil {

	public void mapToActualObject(Student actual, Student student) {
		if(student.getStdName()!=null)
			actual.setStdName(student.getStdName());
		actual.setStdFee(student.getStdFee());
		actual.setCourse(student.getCourse());
		actual.setAddress(student.getAddress());
	}

}
