package com.example.nandhu.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nandhu.exception.ResourceNotfoundException;
import com.example.nandhu.model.Student;

import com.example.nandhu.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/v1/")
public class StudentController {

	@Autowired
	private StudentRepository strs;
	
	
	@GetMapping("/students")
	public List<Student> getStud(){
		return this.strs.findAll();
	}
	
	
	//create Student rest API
	@PostMapping("/students")
	public Student createStud(@RequestBody Student stud) {
		return strs.save(stud);
	}
	
	//get Student by id rest API
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentbyId(@PathVariable Long id){
		Student stud=strs.findById(id).orElseThrow(()-> new ResourceNotfoundException("Student not exist with Id :"+id));
		return ResponseEntity.ok(stud);
	}
	
	//update student rest API
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStud(@PathVariable Long id, @RequestBody Student stud){
		Student s=strs.findById(id).orElseThrow(()-> new ResourceNotfoundException("Student not exist with id:"+id));
		
		s.setFname(stud.getFname());
		s.setLname(stud.getLname());
		s.setEmail(stud.getEmail());
		s.setAddress(stud.getAddress());
		Student updateStud=strs.save(s);
	
		return ResponseEntity.ok(updateStud);
	}
	
	//delete student rest API
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStud(@PathVariable Long id){
		Student stud=strs.findById(id).orElseThrow(()-> new ResourceNotfoundException("Student not exist with id:"+id));
		strs.delete(stud);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
}
