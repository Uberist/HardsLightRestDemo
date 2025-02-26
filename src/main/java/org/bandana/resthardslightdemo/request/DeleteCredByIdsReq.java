package org.bandana.resthardslightdemo.request;

import lombok.Data;

@Data
public class DeleteCredByIdsReq {
    Iterable<Long> credids;
}
