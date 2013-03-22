package de.phahn.climbing.config;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
    
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {  
		
		try {
        
		// create spring application context
		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("de.phahn.climbing"); 
        servletContext.addListener(new ContextLoaderListener(root)); 
        
        // register spring dispatcher servlet
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(root));
        MultipartConfigElement multipartConfigElement =new MultipartConfigElement(null, 500000000, 500000000, 0);
        appServlet.setMultipartConfig(multipartConfigElement);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");     
                       
        // register spring security filter
        FilterRegistration.Dynamic filter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        filter.addMappingForUrlPatterns(null, false, "/*");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}