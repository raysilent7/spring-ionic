package com.raysilent.curso.services;

import com.raysilent.curso.domain.Client;
import com.raysilent.curso.domain.Request;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendRequestConfirmationEmail(Request obj);
    void sendEmail(SimpleMailMessage msg);
    void sendRequestConfirmationHtmlEmail(Request obj);
    void sendHtmlEmail(MimeMessage msg);
    void sendNewPasswordEmail(Client client, String newPass);
}
