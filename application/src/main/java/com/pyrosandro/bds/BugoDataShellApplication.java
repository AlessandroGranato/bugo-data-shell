package com.pyrosandro.bds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class BugoDataShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(BugoDataShellApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            log.debug(" --- BEANS LOADED BY SPRING BOOT:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                log.debug("Loaded Bean: {}", beanName);
            }
            log.debug(" --- BEANS LOADED BY SPRING BOOT - END");
        };
    }
}
