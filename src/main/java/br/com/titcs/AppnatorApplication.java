package br.com.titcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication(scanBasePackages= {"br.com.titcs"})
public class AppnatorApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AppnatorApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<AppnatorApplication> applicationClass = AppnatorApplication.class;
}
