package org.bandana.resthardslightdemo.controllers;

import jakarta.transaction.Transactional;
import org.bandana.resthardslightdemo.db.entity.Users;
import org.bandana.resthardslightdemo.db.repository.CredentialsRepository;
import org.bandana.resthardslightdemo.db.repository.UsersRepository;
import org.bandana.resthardslightdemo.request.UserIdReq;
import org.bandana.resthardslightdemo.request.UserNewReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "https://bandanaclawa.ru:5173")
@RequestMapping(value = "/users")
@Transactional
public class RestControllerUsers {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CredentialsRepository credentialsRepository;
    @PostMapping(value = "/find")
    public ResponseEntity<Boolean> getUsersById(@RequestBody UserNewReq newUser) {
        System.out.println(usersRepository.existsById(Long.valueOf(newUser.getId())));

        if (!usersRepository.existsById(Long.valueOf(newUser.getId()))){
            usersRepository.save(new Users(Long.valueOf(newUser.getId()), newUser.getName(), ""));
            return ResponseEntity.ok(false);
        }
        else
            return ResponseEntity.ok(true);
    }
//    @PostMapping(value = "/add")
//    public ResponseEntity<Boolean> addUser(@RequestBody UserNewReq user) {
//        if(null == usersRepository.getUsersById(Long.valueOf(user.getId()))){
//            Users newUser = usersRepository.save(new Users(Long.valueOf(user.getId()), user.getName(), ""));
//            return ResponseEntity.ok(true);
//        } else{
//            return ResponseEntity.ok(false);
//        }
//    }
}
