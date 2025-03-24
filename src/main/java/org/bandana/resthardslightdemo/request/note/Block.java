package org.bandana.resthardslightdemo.request.note;

import lombok.Data;

@Data
public class Block {
    DataReq data;
    String id;
    String type;

    public Block(DataReq data, String id, String type) {
        this.data = data;
        this.id = id;
        this.type = type;
    }
}
