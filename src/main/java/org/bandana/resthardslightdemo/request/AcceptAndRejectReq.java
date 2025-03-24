package org.bandana.resthardslightdemo.request;

import lombok.Data;

@Data
public class AcceptAndRejectReq {
    private Long credid;
    private Long userid;
}
