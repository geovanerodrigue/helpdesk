package com.helpdesk;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EntityScan(basePackages = "com.helpdesk.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = "com.helpdesk.repository")
@EnableTransactionManagement
@EnableWebMvc
public class HelpdeskApplication implements AsyncConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);


		//System.out.println("Eu rodei aeeeeeeeee");
		//System.out.println(new BCryptPasswordEncoder().encode("123"));

	}

	@Override
	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Assyncrono Thread");
		executor.initialize();

		return executor;
	}

}
