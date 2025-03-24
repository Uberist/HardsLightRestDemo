package org.bandana.resthardslightdemo.request.note;

import lombok.Data;

import java.util.List;

@Data
public class DataNoteReq {
    List<Block> blocks;
    String time;
    String version;

    public DataNoteReq(List<Block> blocks, String time, String version) {
        this.blocks = blocks;
        this.time = time;
        this.version = version;
    }
}
