package org.bandana.resthardslightdemo.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bandana.resthardslightdemo.db.entity.System;
import org.bandana.resthardslightdemo.db.entity.Users;

import java.sql.Timestamp;

@Data
public class CredentialsReq {
    private Long userid;
    private Long id;
    private Iterable<System> system;
    private Iterable<Users> usersids;
    private String login;
    private String password;
    private Users sender;
    private String startaccess;
    private String endaccess;
    private String timesend;
    @Getter
    @Setter
    @JsonSetter("accept")
    private boolean accept;
    @Getter
    @Setter
    @JsonSetter("reject")
    private boolean reject;

    public CredentialsReq(Long id, Iterable<System> system, Iterable<Users> usersids, String login, String password, String startaccess, String endaccess, String timesend, Users sender, boolean accept, boolean reject) {
        this.id = id;
        this.system = system;
        this.usersids = usersids;
        this.login = login;
        this.password = password;
        this.startaccess = startaccess;
        this.endaccess = endaccess;
        this.timesend = timesend;
        this.sender = sender;
        this.accept = accept;
        this.reject = reject;
    }
    public CredentialsReq(Long id, Iterable<System> system, Iterable<Users> usersids, String login, String password, String startaccess, String endaccess, String timesend, Users sender) {
        this.id = id;
        this.system = system;
        this.usersids = usersids;
        this.login = login;
        this.password = password;
        this.startaccess = startaccess;
        this.endaccess = endaccess;
        this.timesend = timesend;
        this.sender = sender;
    }
    public CredentialsReq() {}
//    public CredentialsReq(Long id, Iterable<System> system, Iterable<Users> usersids, String login, String password, Users sender) {
//        this.id = id;
//        this.system = system;
//        this.usersids = usersids;
//        this.login = login;
//        this.password = password;
//        this.sender = sender;
//    }

}
