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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddresssController {

	@Autowired
	private AddressRepo addressRepo;
	
	@PostMapping("/api/addressess")
	public ResponseEntity<Address> saveAllAddress(@RequestBody Address address){
		return new ResponseEntity<>(addressRepo.save(address),HttpStatus.CREATED);
	}
	@GetMapping("/api/addressess")
	public ResponseEntity<List<Address>> getAllStudents(){
		return new ResponseEntity<>(addressRepo.findAll(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable int id){
		Optional<Address> address=addressRepo.findById(id);
		return address.map(value->new ResponseEntity<>(value,HttpStatus.OK))
				.orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteaddress(@PathVariable int id){
		Optional<Address> student=addressRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/id")
	public ResponseEntity<Address> updateAddress(@PathVariable int id,@RequestBody Address updatedAddress){
		Optional<Address> address=addressRepo.findById(id);
		if(address.isPresent()) {
			
			Address s=address.get();
				s.setCity(updatedAddress.getCity());
				s.setStreet(updatedAddress.getStreet());
				s.setZipCode(updatedAddress.getZipCode());
				return new ResponseEntity<>(addressRepo.save(s),HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		}
	
}
