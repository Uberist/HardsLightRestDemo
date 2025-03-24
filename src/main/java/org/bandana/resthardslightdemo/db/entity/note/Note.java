package org.bandana.resthardslightdemo.db.entity.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "note")
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "sender")
    private long sender;
    @Column(name = "begin_date_access")
    private Timestamp beginDateAccess;
    @Column(name = "end_date_access")
    private Timestamp endDateAccess;
    @Column(name = "time_send")
    private Timestamp timeSend;

    public Note(long sender, Timestamp beginDateAccess, Timestamp endDateAccess, Timestamp timeSend) {
        this.sender = sender;
        this.beginDateAccess = beginDateAccess;
        this.endDateAccess = endDateAccess;
        this.timeSend = timeSend;
    }
}
