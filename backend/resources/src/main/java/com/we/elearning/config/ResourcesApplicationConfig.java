package com.we.elearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableJpaRepositories(basePackages = {"com.we.elearning.templates.repositories",
        "com.we.elearning.playgrounds.repositories"})
@EnableWebFlux
@Configuration
public class ResourcesApplicationConfig {

}
