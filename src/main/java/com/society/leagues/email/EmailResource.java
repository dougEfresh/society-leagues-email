package com.society.leagues.email;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class EmailResource {
   private static Logger logger = Logger.getLogger(EmailResource.class);
    @Autowired EmailService emailService;
    @Autowired EmailSender emailSender;

    @RequestMapping(value = "/api/email/send",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public Boolean send(@RequestBody Email email) {
        try {
            emailService.add(new EmailTaskRunner(emailSender, email.getSubject(), email.getBody(), email.getTo()));
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            return false;
        }

        return true;
    }
}
