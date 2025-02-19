package org.bandana.resthardslightdemo.controllers;

import jakarta.transaction.Transactional;
import org.bandana.resthardslightdemo.db.entity.CredGroup;
import org.bandana.resthardslightdemo.db.repository.GroupCredRepositoryToMany;
import org.bandana.resthardslightdemo.db.repository.GroupCredRepositoryToMany;
import org.bandana.resthardslightdemo.request.*;
import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.bandana.resthardslightdemo.db.entity.GroupsCred;
import org.bandana.resthardslightdemo.db.repository.CredentialsRepository;
import org.bandana.resthardslightdemo.db.repository.GroupsCredRepository;
import org.bandana.resthardslightdemo.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@CrossOrigin(origins = "https://bandanaclawa.ru:5173")
public class RestControllerCred {
    @Autowired
    private GroupCredRepositoryToMany groupsCredRepositoryToMany;
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private GroupsCredRepository groupsCredRepository;
    @PostMapping(value = "/getcred")
    public ResponseEntity<Iterable<Credentials>> RestControllerTest(@RequestBody UserIdReq req) {
        Iterable<Credentials> credentials = credentialsRepository.findByUserId(Long.valueOf(req.getUserid()));
        return ResponseEntity.ok(credentials);
    }

    @PostMapping(value = "/deletecred")
    public ResponseEntity<Boolean> RestControllerDelete(@RequestBody DeleteCredReq deleteCredReq) {
        groupsCredRepositoryToMany.updateIdCred(Long.parseLong(deleteCredReq.getCredid()));
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
        List<Long> ListIds = new ArrayList<>();
        for(String el : createGroupsCredReq.getListid()){
            ListIds.add(Long.parseLong(el));
        }
        Iterable<Long> listLongIds = ListIds;
        Iterable<Credentials> listCred = credentialsRepository.findAllById(listLongIds);
        for(Credentials el : listCred){
            CredGroup credGroup = new CredGroup();
            credGroup.setId_group(groupsCred.getId());
            credGroup.setId_cred(el.getId());
            groupsCredRepositoryToMany.save(credGroup);
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
    public ResponseEntity<Iterable<Credentials>> RestControllerGetMarkbook(@RequestBody UserIdReq userIdReq){
        Iterable<Credentials> credentials = credentialsRepository.findByUserIdAndMarkbook(Long.valueOf(userIdReq.getUserid()));
        return ResponseEntity.ok(credentials);
    }
    @PostMapping(value = "/groups/cred/get")
    public ResponseEntity<Iterable<GroupsCred>> RestControllerGetGroups(@RequestBody UserIdReq req){
        List<Long> GroupsIds = new ArrayList<>();
        GroupsIds = groupsCredRepositoryToMany.findIdsGroupById_user(Long.valueOf(req.getUserid()));
        Iterable<GroupsCred> groupsCreds = groupsCredRepository.findAllById(GroupsIds);
        return ResponseEntity.ok(groupsCreds);
    }
    @PostMapping(value = "/groups/cred/delete")
    public ResponseEntity<Boolean> RestControllerGroupCredDelete(@RequestBody DeleteGroupCredReq deleteGroupCredReq){
        Iterable<Credentials> groups = credentialsRepository.findByGroupId(deleteGroupCredReq.getId());
        for(Credentials cred : groups){
            cred.setGroupId(null);
        }
        List<Long> CredGroupIds = groupsCredRepositoryToMany.findIdsGroup_CredById_Cred(deleteGroupCredReq.getId());
        for(Long credGroupId : CredGroupIds){
            groupsCredRepositoryToMany.updateIdGroup(credGroupId);
            groupsCredRepositoryToMany.deleteById(credGroupId);
        }

        groupsCredRepository.deleteById(deleteGroupCredReq.getId());
        return ResponseEntity.ok(true);
    }
    @PostMapping(value = "/groups/cred/edit")
    public ResponseEntity<Boolean> RestControllerGroupCredEdit(@RequestBody EditGroupReq editGroupReq){
        groupsCredRepository.save(new GroupsCred(Long.parseLong(editGroupReq.getGroupid()), editGroupReq.getGroupname()));
        return ResponseEntity.ok(true);
    }
    @PostMapping(value = "/markbook/cred/delete")
    public ResponseEntity<Boolean> RestControllerMarkbookDelete(@RequestBody DeleteGroupCredReq deleteGroupCredReq){
        Credentials credentials = credentialsRepository.findById(deleteGroupCredReq.getId()).get();
        credentials.setMarkbook(false);
        credentialsRepository.save(credentials);
        return ResponseEntity.ok(true);
    }
    @PostMapping(value = "/cred/by/group")
    public ResponseEntity<Iterable<Credentials>> RestControllerGetCredByGroup(@RequestBody GroupIdReq req){
        List<Long> credentialsIds = groupsCredRepositoryToMany.findIdCredByIdgroup(Long.valueOf(req.getGroupid()));
        Iterable<Credentials> credentials = credentialsRepository.findAllById(credentialsIds);
        return ResponseEntity.ok(credentials);
    }
}
