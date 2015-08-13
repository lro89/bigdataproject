package org.fhmuenster.bde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Application Klasse.
 *
 */
@SpringBootApplication
public class UfoProjectApplication extends WebMvcConfigurationSupport {

	private static final String RESOURCE_LOCATION = "classpath:/static/";

	public static void main(String[] args) {
		SpringApplication.run(UfoProjectApplication.class, args);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ThymeleafLayoutInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Damit die statischen resourcen geladen werden können
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(
					RESOURCE_LOCATION);
		}
	}
}
