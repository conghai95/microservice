package com.example.cmc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    // That's all here for now. We'll add more auditing configurations later.
}
//To enable JPA Auditing, we’ll need to add @EnableJpaAuditing annotation to our main class
//or any other configuration classes.