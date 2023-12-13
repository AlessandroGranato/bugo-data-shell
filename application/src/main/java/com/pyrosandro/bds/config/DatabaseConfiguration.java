package com.pyrosandro.bds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"com.pyrosandro.bds.repository"})
@EnableTransactionManagement
public class DatabaseConfiguration {
}
