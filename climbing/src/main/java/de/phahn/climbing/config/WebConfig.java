package de.phahn.climbing.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;


@Configuration
@EnableWebMvc
@EnableMongoRepositories("de.phahn.climbing.repositories")
@ComponentScan("de.phahn.climbing")
@ImportResource("classpath:spring-security.xml")
public class WebConfig extends WebMvcConfigurerAdapter {
		
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}
	
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(ServletContextTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
 
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine);		
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasenames(new String[] { "WEB-INF/i18n/messages", "classpath:build" });
		bundle.setCacheSeconds(1);
		bundle.setFallbackToSystemLocale(false);
		return bundle;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public MongoClient mongo() throws Exception {
		MongoClient mongo = new MongoClient();
		return mongo;
	}

	@Bean
	public MongoDbFactory mongoDbFactory(MongoClient mongo) throws Exception {
		return new SimpleMongoDbFactory(mongo, "climbing");
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) throws Exception {
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		// set write concern to safe so we get unique constraint violations
		template.setWriteConcern(WriteConcern.SAFE);
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
