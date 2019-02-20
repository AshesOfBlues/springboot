package com.alex.blog.blogstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan(basePackages = "com.alex.*")
@EnableElasticsearchRepositories(basePackages = "com.alex.*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

