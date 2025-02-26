package org.bandana.resthardslightdemo.controllers;

import org.bandana.resthardslightdemo.db.entity.CredSystem;
import org.bandana.resthardslightdemo.db.entity.System;
import org.bandana.resthardslightdemo.db.repository.CredSystemRepository;
import org.bandana.resthardslightdemo.db.repository.SystemRepository;
import org.bandana.resthardslightdemo.request.CredentialsReq;
import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.bandana.resthardslightdemo.db.repository.CredentialsRepository;
import org.bandana.resthardslightdemo.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://bandanaclawa.ru:5173")
public class RestControllerSaveCred {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SystemRepository systemRepository;
    @Autowired
    private CredSystemRepository credSystemRepository;
    @PostMapping(value = "/savecred")
    public boolean RestControllerTest(@RequestBody CredentialsReq credentialsReq) {
        Credentials credentials = new Credentials(
                Long.valueOf(credentialsReq.getUserid()),
                credentialsReq.getUsername(),
                credentialsReq.getLogin(),
                credentialsReq.getPassword()
        );
        Credentials newCred = credentialsRepository.save(credentials);
        for(System system : credentialsReq.getSystem())
            credSystemRepository.save(new CredSystem(newCred.getId(), system.getId()));
        return true;
    }
}
