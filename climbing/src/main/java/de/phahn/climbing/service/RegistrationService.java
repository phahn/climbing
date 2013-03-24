package de.phahn.climbing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import de.phahn.climbing.controller.command.RegistrationCommand;
import de.phahn.climbing.model.User;

@Service
public class RegistrationService {
		
	@Autowired
	private MongoOperations mongoTemplate;
	
	public User register(RegistrationCommand command) {
		User user = new User();
		user.setUsername(command.getUsername());
		user.setEmail(command.getEmail());
		user.setPassword(command.getPassword());
		mongoTemplate.save(user);
		return user;
	}
	
	public User findUserByUsername(String username) {
		return mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
	}
	
}
