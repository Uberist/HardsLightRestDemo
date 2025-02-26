package org.bandana.resthardslightdemo.request;

import lombok.Data;
import org.bandana.resthardslightdemo.db.entity.System;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CredentialsReq {
    private Long id;
    private String username;
    private Iterable<System> system;
    private String login;
    private String password;
    private String userid;

    public CredentialsReq(Long id, String username, Iterable<System> system, String login, String password, String userid) {
        this.id = id;
        this.username = username;
        this.system = system;
        this.login = login;
        this.password = password;
        this.userid = userid;
    }
}
