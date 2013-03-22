package de.phahn.climbing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import de.phahn.climbing.model.Requirement;

@Service
public class RequirementService {
		
	@Autowired
	private MongoOperations mongoTemplate;
	
	public void save(Requirement requirement) {
		mongoTemplate.save(requirement);
	}
	
	public void updateRequirement(String id, String key, Object value) {
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)), Update.update(key, value), Requirement.class);
	}

	public List<Requirement> list() {		
		return mongoTemplate.findAll(Requirement.class);
	}

}
