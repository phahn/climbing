package de.phahn.climbing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;

public interface MongoDbFactoryConfiguration {

	@Bean
	public abstract MongoDbFactory mongoDbFactory() throws Exception;

}