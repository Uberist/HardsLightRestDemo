package org.bandana.resthardslightdemo.request;

import lombok.Data;

import java.util.List;
@Data
public class IdsReq {
    List<Long> ids;

    public IdsReq(List<Long> ids) {
        this.ids = ids;
    }
}
