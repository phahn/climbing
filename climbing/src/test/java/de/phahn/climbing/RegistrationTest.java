package de.phahn.climbing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.phahn.climbing.config.BusinessConfiguration;
import de.phahn.climbing.controller.command.RegistrationCommand;
import de.phahn.climbing.model.User;
import de.phahn.climbing.service.RegistrationService;
@ContextConfiguration(classes={BusinessConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("default")
@WebAppConfiguration
public class RegistrationTest {
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private MongoOperations mongoTemplate;
	
	@Before
	public void clear() {
		mongoTemplate.dropCollection(User.class);
	}
	
	@Test
	public void registerNewUser() {
		RegistrationCommand cmd = new RegistrationCommand();
		cmd.setUsername("test");
		cmd.setEmail("test@test.com");
		cmd.setPassword("123456");
		User user = service.register(cmd);
				
			assertEquals(user.getUsername(), cmd.getUsername());
	}
	
	@Test
	public void duplicateUser() {
		RegistrationCommand cmd = new RegistrationCommand();
		cmd.setUsername("test");
		cmd.setEmail("test@test.com");
		cmd.setPassword("123456");
		User user = service.register(cmd);
		user = service.register(cmd);
	}

}
