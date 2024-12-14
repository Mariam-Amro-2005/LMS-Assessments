package com.example.lms.assessments.configuration;

import com.example.lms.assessments.repository.AssessmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssessmentConfig {
    @Bean
    CommandLineRunner commandLineRunner(AssessmentRepository repository){
        return args -> {};
    }
}
