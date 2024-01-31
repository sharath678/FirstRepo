package com.example.demo;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveAllStudents(@RequestBody Student student){
		return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
	}
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id){
		Optional<Student> student=studentRepo.findById(id);
		return student.map(value->new ResponseEntity<>(value,HttpStatus.OK))
				.orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int id){
		Optional<Student> student=studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/id")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student updatedStudent){
		Optional<Student> student=studentRepo.findById(id);
		if(student.isPresent()) {
			
				Student s=student.get();
				s.setName(updatedStudent.getName());
				s.setDob(updatedStudent.getDob());
				s.setClassess(updatedStudent.getClassess());
				s.setDivision(updatedStudent.getDivision());
				s.setGender(updatedStudent.getGender());
				return new ResponseEntity<>(studentRepo.save(s),HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		}
	
}
