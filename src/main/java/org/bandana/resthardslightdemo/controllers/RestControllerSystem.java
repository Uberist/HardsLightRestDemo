package org.bandana.resthardslightdemo.controllers;

import org.bandana.resthardslightdemo.db.entity.credentials.System;
import org.bandana.resthardslightdemo.db.repository.CredentialsRepository;
import org.bandana.resthardslightdemo.db.repository.SystemRepository;
import org.bandana.resthardslightdemo.request.UserIdReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "https://bandanaclawa.ru:5173")
@Controller
public class RestControllerSystem {
    @Autowired
    public CredentialsRepository credentialsRepository;
    @Autowired
    public SystemRepository systemRepository;
    @PostMapping(value = "/cred/system/get")
    public ResponseEntity<Iterable<String>> getSystem(@RequestBody UserIdReq userid) {
        return ResponseEntity.ok(credentialsRepository.findSystemByUserId(Long.valueOf(userid.getUserid())));
    }
    @PostMapping(value = "/system/get")
    public ResponseEntity<Iterable<System>> getSystem() {
        return ResponseEntity.ok(systemRepository.findAll());
    }
}
