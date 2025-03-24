package org.bandana.resthardslightdemo.request.note;

import lombok.Data;

@Data
public class DataReq {
    String text;

    public DataReq(String text) {
        this.text = text;
    }
}
