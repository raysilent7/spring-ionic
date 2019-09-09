package com.raysilent.curso.config;

import com.raysilent.curso.services.EmailService;
import com.raysilent.curso.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
