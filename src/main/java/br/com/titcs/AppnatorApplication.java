package br.com.titcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication(scanBasePackages= {"br.com.titcs"})
public class AppnatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppnatorApplication.class, args);
	}

}
