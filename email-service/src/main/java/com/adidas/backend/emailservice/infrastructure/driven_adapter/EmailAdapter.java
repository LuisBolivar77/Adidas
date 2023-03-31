package com.adidas.backend.emailservice.infrastructure.driven_adapter;

import com.adidas.backend.emailservice.domain.AdiClubMember;
import com.adidas.backend.emailservice.domain.Event;
import com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway.EmailGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EmailAdapter implements EmailGateway {

    private final JavaMailSender emailSender;

    private Logger logger = Logger.getLogger("AdiLogger");
    public void sendSimpleMessage(String subject, String text, String email) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);
        logger.info(email.concat(" / ").concat(subject).concat(" / ").concat(text));
        /*
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
         */
    }

}
