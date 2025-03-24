package org.bandana.resthardslightdemo.request.note;

import lombok.Data;
import org.bandana.resthardslightdemo.db.entity.Users;

import java.util.List;
@Data
public class CreateNoteReq {
    Users sender;
    List<Users> usersids;
    DataNoteReq data;
    boolean accept;
    boolean reject;
    String startaccess;
    String endaccess;
    String timesend;

    public CreateNoteReq(Users sender, List<Users> usersids, DataNoteReq data, boolean accept, boolean reject, String startaccess, String endaccess, String timesend) {
        this.sender = sender;
        this.usersids = usersids;
        this.data = data;
        this.accept = accept;
        this.reject = reject;
        this.startaccess = startaccess;
        this.endaccess = endaccess;
        this.timesend = timesend;
    }
}
