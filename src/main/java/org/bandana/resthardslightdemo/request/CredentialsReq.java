package org.bandana.resthardslightdemo.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CredentialsReq {
    private String username;
    private String system;
    private String login;
    private String password;
    private String userid;
}
