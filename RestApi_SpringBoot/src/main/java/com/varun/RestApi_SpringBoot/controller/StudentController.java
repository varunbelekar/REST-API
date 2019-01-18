package com.varun.RestApi_SpringBoot.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.varun.RestApi_SpringBoot.dao.StudentDAO;
import com.varun.RestApi_SpringBoot.model.Student;

@RestController
public class StudentController {
	
	@Autowired
	StudentDAO studentDao;
	
	@PostMapping("/student")
	public Student addStudent(Student s){
		studentDao.save(s);
		return s;
	}
	
	
	@GetMapping("/student")
	public List<Student> getStudents(){
		return studentDao.findAll();
	}
	
	@GetMapping("/student/{id}")
	public Optional<Student> getStudentById(@PathVariable int id){
		return studentDao.findById(id);
	}
	
	@DeleteMapping("/student/{id}")
	public String deleteStudent(@PathVariable int id){
		Student s=studentDao.getOne(id);
		studentDao.delete(s);
		return "delete";
	}
	
	@PutMapping("/student/{id}")
	public Student saveOrUpdateStudent(Student s){
		studentDao.save(s);
		return s;
	}
	
	/**
	 * This method is used to return list of students in JSON formate.
	 * @return
	 */
	@RequestMapping(path="/students",produces="application/xml")
	@ResponseBody
	public List<Student> getStudentInJson(){
		return studentDao.findAll();
	}
	
	
	
	
}


