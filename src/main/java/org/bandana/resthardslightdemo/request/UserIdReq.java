package org.bandana.resthardslightdemo.request;

import lombok.Data;

@Data
public class UserIdReq {
    public int userid;

    public UserIdReq(int userid) {
        this.userid = userid;
    }
}
