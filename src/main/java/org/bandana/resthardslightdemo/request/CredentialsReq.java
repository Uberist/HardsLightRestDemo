package org.bandana.resthardslightdemo.request;

import lombok.Data;
import org.bandana.resthardslightdemo.db.entity.System;
import org.bandana.resthardslightdemo.db.entity.Users;

@Data
public class CredentialsReq {
    private Long id;
    private Iterable<System> system;
    private Iterable<Users> usersids;
    private String login;
    private String password;
    private Users sender;

    public CredentialsReq(Long id, Iterable<System> system,Iterable<Users> usersids, String login, String password, Users sender) {
        this.id = id;
        this.system = system;
        this.usersids = usersids;
        this.login = login;
        this.password = password;
        this.sender = sender;
    }
}
