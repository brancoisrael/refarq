package br.com.titcs.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"br.com.titcs.repository"})
@EntityScan(basePackages = {"br.com.titcs.domain"})
@Configuration
public class PostgreConfig {

}

