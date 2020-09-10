package com.satyait.restController;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyait.model.Student;
import com.satyait.service.impl.StudentServiceImpl;
import com.satyait.util.StudentUtil;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	
	private Logger log = LoggerFactory.getLogger(StudentRestController.class);
	
	@Autowired
	private StudentServiceImpl service;
	
	@Autowired
	private StudentUtil util;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveStudent(@RequestBody Student s){
		
		log.info("Entered into method with Student data to save");
		
		ResponseEntity<String> response=null;
		
		try {
			log.info("About to call save Operation");
			
			Integer studentId = service.saveStudent(s);
			log.debug("Student saved with id "+studentId);
			
			String body="Student '"+studentId+"'Created";
			response=new ResponseEntity<String>(body,HttpStatus.CREATED);
			
			log.info("Sucess response constructed");
			
		} catch (Exception e) {
			log.error("Unable to save student : problem is :"+e.getMessage());
			response=new ResponseEntity<String>("Unable to Create Student",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		log.info("About to Exist save method with Response");
		return response;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudent(){
		log.info("Entered into method to fetch Students data");
		ResponseEntity<?> response=null;
		try {
			log.info("About to call fetch student service");
 			List<Student> allStudent = service.getAllStudent();
			
			if (allStudent!=null && !allStudent.isEmpty()) {
				log.info("Data is not empty=>"+allStudent.size());
				allStudent.sort((s1,s2)->s1.getStdId().compareTo(s2.getStdId()));
				response=new ResponseEntity<List<Student>>(allStudent,HttpStatus.OK);	
			}else {
				log.info("No Student exist: size "+allStudent.size());
				response=new ResponseEntity<String>("No Student Record Found !!",HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("Unable to fetch students : problem is :"+e.getMessage());
			response=new ResponseEntity<String>("Unable to Fetch Student Record",HttpStatus.INTERNAL_SERVER_ERROR);	
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return response;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneStudent(@PathVariable Integer id){
		log.info("Entered into Get one Student method");
		ResponseEntity<?> response=null;
		try {
			log.info("About to make service call to fetch one record");
			Optional<Student> opt = service.getOneStudent(id);
			if (opt.isPresent()) {
				log.info("Student exist=>"+id);
				response=new ResponseEntity<Student>(opt.get(),HttpStatus.OK);
			}else {
				log.warn("Given Student id not exist=>"+id);
				response=new ResponseEntity<String>("Student '"+id+"' Not Exist",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to process request fetch " + e.getMessage());
			response=new ResponseEntity<String>("Unable to Process Request Fetch ",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeStudent(@PathVariable Integer id){
		log.info("About to make service call for data check");
		ResponseEntity<String> response=null;
		try {
			boolean isExist = service.isStudentExist(id);
			
			if (isExist) {
				service.deleteStudent(id);
				log.info("Student exist with given id and deleted=>"+id);
				response=new ResponseEntity<String>("Student '"+id+"' is Deleted",HttpStatus.OK);
			}else {
				log.warn("Given Student id not exist =>"+id);
				response=new ResponseEntity<String>("Student '"+id+"' is Not Exist",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to perform Delete Operation =>" + e.getMessage());
			response=new ResponseEntity<String>("Unable to Delete Student Record ",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return response; 
	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<String> updateStudent(
			@PathVariable Integer id,
			@RequestBody Student student
			)
	{
		log.info("Entered into Update method");
		ResponseEntity<String> resp =null;
		try {
			log.info("About to check given id exist or not db");
			Optional<Student> opt =  service.getOneStudent(id);
			if(opt.isPresent()) {
				log.info("Student present in database");
				Student actual = opt.get();
				util.mapToActualObject(actual,student);
				service.updateStudent(actual);
				resp = new ResponseEntity<String>(
						"Student '"+id+"' Updated", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.OK
						);
				log.info("Student update done successfully");
			} else {
				log.info("Student not exist=>"+id);
				resp = new ResponseEntity<String>(
						"Student '"+id+"' not found", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.BAD_REQUEST
						);
			}
		} catch (Exception e) {
			log.error("Unable to perform Update Operation=>" + e.getMessage() );
			resp = new ResponseEntity<String>(
					"Unable to process Update",
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
}
