package org.bandana.resthardslightdemo.controllers;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.bandana.resthardslightdemo.db.entity.CredGroup;
import org.bandana.resthardslightdemo.db.entity.Users;
import org.bandana.resthardslightdemo.db.repository.*;
import org.bandana.resthardslightdemo.db.repository.GroupCredRepositoryToMany;
import org.bandana.resthardslightdemo.request.*;
import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.bandana.resthardslightdemo.db.entity.GroupsCred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private CredSystemRepository credSystemRepository;
    @Autowired
    private CredentialsUserRepository credentialsUserRepository;
    @Autowired
    SystemRepository systemRepository;
    @PostMapping(value = "/users/get")
    public ResponseEntity<Iterable<Users>> getUser() {
        return ResponseEntity.ok(usersRepository.findAll());
    }
    @PostMapping(value = "/getcred")
    public ResponseEntity<Iterable<CredentialsReq>> RestControllerTest(@RequestBody UserIdReq req) {
        Iterable<Credentials> credentials = credentialsRepository.findAllCredByUserId(Long.valueOf(req.getUserid()));
        List<CredentialsReq> creds = new ArrayList<>();
        for(Credentials cred : credentials)
            creds.add(new CredentialsReq(
                    cred.getId(),
                    systemRepository.findAllById(credSystemRepository.findByCredId(cred.getId())),
                    usersRepository.findAllById(credentialsUserRepository.findUserIdByCredId(cred.getId())),
                    cred.getLogin(),
                    cred.getPassword(),
                    cred.getBegindateaccess().toString(),
                    cred.getEnddateaccess().toString(),
                    cred.getTimesend().toString(),
                    usersRepository.findById(cred.getSender()).get()));
        return ResponseEntity.ok(creds);
    }
    @PostMapping(value = "/cred/newcred/get")
    public ResponseEntity<Iterable<CredentialsReq>> RestControllerGetNewCred(@RequestBody UserIdReq req) {
        Iterable<Credentials> credentials = credentialsRepository.findNewCredentialsByUserId(Long.valueOf(req.getUserid()));
        List<CredentialsReq> creds = new ArrayList<>();
        for(Credentials cred : credentials)
            creds.add(new CredentialsReq(
                    cred.getId(),
                    systemRepository.findAllById(credSystemRepository.findByCredId(cred.getId())),
                    usersRepository.findAllById(credentialsUserRepository.findUserIdByCredId(cred.getId())),
                    cred.getLogin(),
                    cred.getPassword(),
                    cred.getBegindateaccess().toString(),
                    cred.getEnddateaccess().toString(),
                    cred.getTimesend().toString(),
                    usersRepository.findById(cred.getSender()).get(),
                    cred.getAccept(),
                    cred.getReject()));
        return ResponseEntity.ok(creds);
    }
    @PostMapping(value = "/deletecred")
    public ResponseEntity<Boolean> RestControllerDelete(@RequestBody DeleteCredReq deleteCredReq) {
        groupsCredRepositoryToMany.updateIdCred(deleteCredReq.getCredid());
        credSystemRepository.updateByCred_id(deleteCredReq.getCredid());
        credentialsUserRepository.updateCredByCredId(deleteCredReq.getCredid());
        credentialsRepository.deleteById(deleteCredReq.getCredid());
        return ResponseEntity.ok(true);
    }
    @PostMapping(value = "/cred/delete/byids")
    public ResponseEntity RestControllerDeleteCred(@RequestBody DeleteCredByIdsReq deleteCredByIdsReq){
        for(Long id : deleteCredByIdsReq.getCredids()){
            groupsCredRepositoryToMany.updateIdCred(id);
            credSystemRepository.updateByCred_id(id);
            credentialsUserRepository.updateCredByCredId(id);
        }
        credentialsRepository.deleteAllById(deleteCredByIdsReq.getCredids());
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
    public ResponseEntity<Iterable<CredentialsReq>> RestControllerGetMarkbook(@RequestBody UserIdReq userIdReq){
        Iterable<Credentials> credentials = credentialsRepository.findByUserIdAndMarkbook(Long.valueOf(userIdReq.getUserid()));
        List<CredentialsReq> credentialsReqs = new ArrayList<>();
        for(Credentials cred : credentials){
            credentialsReqs.add(new CredentialsReq(
                    cred.getId(),
                    systemRepository.findAllById(credSystemRepository.findByCredId(cred.getId())),
                    usersRepository.findAllById(credentialsUserRepository.findUserIdByCredId(cred.getId())),
                    cred.getLogin(),
                    cred.getPassword(),
                    cred.getBegindateaccess().toString(),
                    cred.getEnddateaccess().toString(),
                    cred.getTimesend().toString(),
                    usersRepository.findById(cred.getSender()).get()
            ));
        }
        return ResponseEntity.ok(credentialsReqs);
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
    public ResponseEntity<Iterable<CredentialsReq>> RestControllerGetCredByGroup(@RequestBody GroupIdReq req){
        List<Long> credentialsIds = groupsCredRepositoryToMany.findIdCredByIdgroup(Long.valueOf(req.getGroupid()));
        Iterable<Credentials> credentials = credentialsRepository.findAllById(credentialsIds);
        List<CredentialsReq> credentialsReqs = new ArrayList<>();
        for(Credentials cred : credentials){
            credentialsReqs.add(new CredentialsReq(
                    cred.getId(),
                    systemRepository.findAllById(credSystemRepository.findByCredId(cred.getId())),
                    new ArrayList<Users>(),
                    cred.getLogin(),
                    cred.getPassword(),
                    cred.getBegindateaccess().toString(),
                    cred.getEnddateaccess().toString(),
                    cred.getTimesend().toString(),
                    usersRepository.findById(cred.getSender()).get()
            ));
        }
        return ResponseEntity.ok(credentialsReqs);
    }
    @PostMapping(value = "/cred/accept")
    public ResponseEntity<Boolean> RestControllerAccept(@RequestBody DeleteCredReq credId){
        credentialsRepository.accept(credId.getCredid());
        return ResponseEntity.ok(true);
    }
    @PostMapping(value = "/cred/reject")
    public ResponseEntity<Boolean> RestControllerReject(@RequestBody DeleteCredReq credId){
        credentialsRepository.reject(credId.getCredid());
        return ResponseEntity.ok(true);
    }
}
