package com.raysilent.curso.config;

import com.raysilent.curso.services.DBService;
import com.raysilent.curso.services.EmailService;
import com.raysilent.curso.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbservice;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbservice.instantiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
