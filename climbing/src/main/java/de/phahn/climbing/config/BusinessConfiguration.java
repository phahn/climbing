package de.phahn.climbing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories("de.phahn.climbing.repositories")
@ComponentScan("de.phahn.climbing")
public class BusinessConfiguration {
	@Autowired
    private MongoDbFactoryConfiguration mongoDbConfiguration;


	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) throws Exception {
		MongoTemplate template = new MongoTemplate(mongoDbFactory);		
		return template;
	}

	@Bean
	public MongoMappingContext mappingContext() {
		return new MongoMappingContext();
	}

	@Bean
	public MongoConverter mongoConverter(MongoDbFactory mongoDbFactory, MongoMappingContext mappingContext) {
		return new MappingMongoConverter(mongoDbFactory, mappingContext);
	}

	@Bean
	public GridFsTemplate gridTemplate(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter) throws Exception {
		return new GridFsTemplate(mongoDbFactory, mongoConverter);
	}
}
