package com.github.selbovi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.github.selbovi.repo")
public class JpaConfig {

}
