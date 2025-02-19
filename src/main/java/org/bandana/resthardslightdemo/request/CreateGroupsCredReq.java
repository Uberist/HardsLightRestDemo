package org.bandana.resthardslightdemo.request;

import lombok.Data;

@Data
public class CreateGroupsCredReq {
    private Iterable<String> listid;
    private String userid;
}
