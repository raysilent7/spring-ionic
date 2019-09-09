package com.raysilent.curso.services;

import com.raysilent.curso.domain.Client;
import com.raysilent.curso.repositories.ClientRepository;
import com.raysilent.curso.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClientRepository repo;
    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private EmailService emailService;
    
    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Client client = repo.findByEmail(email);
        if (client == null) {
            throw new ObjectNotFoundException("Email not found.");
        }

        String newPass = newPassword();
        client.setPassword(pe.encode(newPass));
        repo.save(client);
        emailService.sendNewPasswordEmail(client, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) {//generate a number between 0 - 9
            return (char) (rand.nextInt(10) + 48);
        }
        else if (opt == 1) {//generate a uppercase character between A-Z
            return (char) (rand.nextInt(26) + 65);
        }
        else {//generate a lowercase character between a-z
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
