package com.rsh.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsh.model.User;
import com.rsh.service.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	//@RequestMapping(method = RequestMethod.GET)
//	@GetMapping(value="")
//	public List<User> findAll(){
//		return userService.findAll();
//	}
	
//	@GetMapping(value="/{id}")
//	public User findById(@PathVariable  Long id) {
//		Optional<User> userOpt = userService.findById(id);
//		if(userOpt.isPresent()) {
//			return userOpt.get();
//		}
//		return null;
//	}
	
	@GetMapping(value="/list")
	public ResponseEntity<?>  findAll(){
		
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value="/paging")
	public ResponseEntity<?>  findAll(Pageable pageable){
		
		return new ResponseEntity<Page<User>>(userService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable  Long id) {
		Optional<User> userOpt = userService.findById(id);
		if(userOpt.isPresent()) {
			return new ResponseEntity<User>(userOpt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findByCriteria(@RequestParam(name = "criteria", required = true) String criteria,
			@RequestParam(name="searchItem", required = true) String searchItem) {
		return new ResponseEntity<List<User>>(userService.findByCriteria(criteria, searchItem) , HttpStatus.OK);
	}
	
	
	
	@PostMapping(value="/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user){
		userService.add(user);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Optional<User> optUser = userService.delete(id);
		
		if(optUser.isPresent()) {
			return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user){
		
		Optional<User> optUser = userService.update(user);
		
		if(optUser.isPresent()) {
			return new ResponseEntity<User>(optUser.get(), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
