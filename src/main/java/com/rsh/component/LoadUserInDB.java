package com.rsh.component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rsh.model.User;
import com.rsh.repository.UserRepository;


@Component
@Transactional
public class LoadUserInDB implements CommandLineRunner{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) throws Exception {
//		User user = new User("Natasha", "Petrova", 36, "RUSSIA");
//		userRepository.save(user);
//		user = new User( "Anna", "Ivanova", 36, "RUSSIA");
//		userRepository.save(user);
//		user = new User("Boris", "Dorisov", 46, "USA");
//		userRepository.save(user);
//		user = new User("Doris", "Borisov", 26, "GERMANI");
//		userRepository.save(user);
		
		
		User user1 = new User("ivanpetrov", "123", "ivan", "Petrov", 36, "RUSSIA");
		User user2 = new User("ivanok", UUID.randomUUID().toString(), "Ivan", "Vladimirov", 36, "RUSSIA");
		User user3 = new User("khankhan", UUID.randomUUID().toString(), "Khan", "khan", 46, "USA");
		User user4 = new User("savvlad", UUID.randomUUID().toString(), "Savka", "Vladimirovna", 26, "GERMANI");
		User user5 = new User("annaivanova", "123", "Anna", "Ivanova", 36, "RUSSIA");
		User user6 = new User("irinadorova", "123", "Irina", "Dorova", 36, "RUSSIA");
		User user7 = new User("sallycargo", "123", "Sally", "Cargo", 46, "USA");
		User user8 = new User("alexv", "123", "Alex", "Vidko", 26, "GERMANI");
		User user9 = new User("ivanvidso", "123", "Ivan", "Vidso", 46, "USA");
		User user10 = new User("annashez", "123", "Anna", "Shezamova", 26, "GERMANI");
		
		List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
				user8, user9, user10);
		
		usersList = usersList.stream().map(user -> {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return user;
		}).collect(Collectors.toList());
		
		userRepository.saveAll(usersList);
		
	}

}
