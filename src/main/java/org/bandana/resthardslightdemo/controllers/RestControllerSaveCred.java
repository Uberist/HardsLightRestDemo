package org.bandana.resthardslightdemo.controllers;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bandana.resthardslightdemo.db.entity.*;
import org.bandana.resthardslightdemo.db.entity.System;
import org.bandana.resthardslightdemo.db.repository.*;
import org.bandana.resthardslightdemo.request.CredentialsReq;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
@Log4j2
@RestController
@CrossOrigin(origins = "https://bandanaclawa.ru:5173")
public class RestControllerSaveCred {
    private static final Logger logger = LogManager.getLogger(RestControllerSaveCred.class);
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SystemRepository systemRepository;
    @Autowired
    private CredentialsUserRepository credentialsUserRepository;
    @Autowired
    private CredSystemRepository credSystemRepository;
    @PostMapping(value = "/savecred")
    public boolean RestControllerTest(@RequestBody CredentialsReq credentialsReq) {
        Credentials credentials = new Credentials(
                credentialsReq.getLogin(),
                credentialsReq.getPassword(),
                credentialsReq.getUserid(),
                Timestamp.valueOf(credentialsReq.getStartaccess().replace('T', ' ') + ":00"),
                Timestamp.valueOf(credentialsReq.getEndaccess().replace('T', ' ') + ":00"),
                Timestamp.valueOf(credentialsReq.getTimesend().replace('T', ' ') + ":00"),
                credentialsReq.isAccept(),
                credentialsReq.isReject()
        );
        Credentials newCred = credentialsRepository.save(credentials);
        for(System system : credentialsReq.getSystem())
            credSystemRepository.save(new CredSystem(newCred.getId(), system.getId()));
        for(Users user : credentialsReq.getUsersids())
            credentialsUserRepository.save(new CredUser(newCred.getId(), user.getId()));
        return true;
    }
}
