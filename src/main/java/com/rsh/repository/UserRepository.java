package com.rsh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rsh.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByUsername(String searchItem);
	
	public List<User> findByFirstName(String searchItem);
	
	public List<User> findByLastName(String searchItem);
	
	public List<User> findByAge(Integer searchItem);
	
	public List<User> findByCountry(String searchItem);
	

}
