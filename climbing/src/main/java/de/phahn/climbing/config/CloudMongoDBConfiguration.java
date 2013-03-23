package de.phahn.climbing.config;

import org.cloudfoundry.runtime.service.document.CloudMongoDbFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;

@Configuration
@Profile("cloud")
public class CloudMongoDBConfiguration implements MongoDbFactoryConfiguration {
	
	@Override
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {		
		 CloudMongoDbFactoryBean factory = new CloudMongoDbFactoryBean();
	     factory.afterPropertiesSet();
	     return factory.getObject();
	}	
}
