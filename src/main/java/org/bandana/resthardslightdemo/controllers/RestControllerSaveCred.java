package org.bandana.resthardslightdemo.controllers;

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
@CrossOrigin(origins = "https://localhost:5173")
public class RestControllerSaveCred {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @PostMapping(value = "/savecred")
    public boolean RestControllerTest(@RequestBody CredentialsReq credentialsReq) {
        Credentials credentials = new Credentials(
                1L,
                credentialsReq.getUsername(),
                credentialsReq.getSystem(),
                credentialsReq.getLogin(),
                credentialsReq.getPassword()
        );
        credentialsRepository.save(credentials);
        return true;
    }
}
