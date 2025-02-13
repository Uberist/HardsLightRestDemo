package org.bandana.resthardslightdemo.controllers;

import jakarta.transaction.Transactional;
import org.bandana.resthardslightdemo.request.CreateGroupsCredReq;
import org.bandana.resthardslightdemo.request.DeleteCredReq;
import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.bandana.resthardslightdemo.db.entity.GroupsCred;
import org.bandana.resthardslightdemo.db.repository.CredentialsRepository;
import org.bandana.resthardslightdemo.db.repository.GroupsCredRepository;
import org.bandana.resthardslightdemo.db.repository.UsersRepository;
import org.bandana.resthardslightdemo.request.DeleteGroupCredReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Transactional
@Controller
@CrossOrigin(origins = "https://localhost:5173")
public class RestControllerCred {
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private GroupsCredRepository groupsCredRepository;
    @PostMapping(value = "/getcred")
    public ResponseEntity<Iterable<Credentials>> RestControllerTest() {
        Iterable<Credentials> credentials = credentialsRepository.findAll();
        return ResponseEntity.ok(credentials);
    }

    @PostMapping(value = "/deletecred")
    public ResponseEntity<Boolean> RestControllerDelete(@RequestBody DeleteCredReq deleteCredReq) {
        credentialsRepository.deleteById(Long.parseLong(deleteCredReq.getCredid()));
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/groups/cred/create")
    public ResponseEntity<Boolean> RestControllerCreate(@RequestBody CreateGroupsCredReq createGroupsCredReq){
        int counter = 0;
        for(String el : createGroupsCredReq.getListid()){
            counter++;
        }
        GroupsCred groupsCred = groupsCredRepository.save(new GroupsCred(String.valueOf(counter) + " Учетных записей"));

        for(String el : createGroupsCredReq.getListid()){
            Credentials credentials = credentialsRepository.findById(Long.parseLong(el)).get();
            credentials.setGroupId(groupsCred.getId());
            credentialsRepository.save(credentials);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/markbook/cred/create")
    public ResponseEntity<Boolean> RestControllerMarkbookCreate(@RequestBody CreateGroupsCredReq createGroupsCredReq){
        for(String el : createGroupsCredReq.getListid()){
            Credentials credentials = credentialsRepository.findById(Long.parseLong(el)).get();
            credentials.setMarkbook(true);
            credentialsRepository.save(credentials);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/markbook/cred/get")
    public ResponseEntity<Iterable<Credentials>> RestControllerGetMarkbook(){
        Iterable<Credentials> credentials = credentialsRepository.findByMarkbook(true);
        return ResponseEntity.ok(credentials);
    }
    @PostMapping(value = "/groups/cred/get")
    public ResponseEntity<Iterable<GroupsCred>> RestControllerGetGroups(){
        Iterable<GroupsCred> groups = groupsCredRepository.findAll();
        return ResponseEntity.ok(groups);
    }
    @PostMapping(value = "/groups/cred/delete")
    public ResponseEntity<Boolean> RestControllerGroupCredDelete(@RequestBody DeleteGroupCredReq deleteGroupCredReq){
        Iterable<Credentials> groups = credentialsRepository.findByGroupId(deleteGroupCredReq.getId());
        for(Credentials cred : groups){
            cred.setGroupId(null);
        }
        groupsCredRepository.deleteById(deleteGroupCredReq.getId());
        return ResponseEntity.ok(true);
    }
    @PostMapping(value = "/markbook/cred/delete")
    public ResponseEntity<Boolean> RestControllerMarkbookDelete(@RequestBody DeleteGroupCredReq deleteGroupCredReq){
        Credentials credentials = credentialsRepository.findById(deleteGroupCredReq.getId()).get();
        credentials.setMarkbook(false);
        credentialsRepository.save(credentials);
        return ResponseEntity.ok(true);
    }
}
