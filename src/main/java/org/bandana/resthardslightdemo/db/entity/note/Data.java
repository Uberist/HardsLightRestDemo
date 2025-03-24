package org.bandana.resthardslightdemo.db.entity.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "data")
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "idnote")
    private long idnote;
    @Column(name = "time")
    private String time;
    @Column(name = "version")
    private String version;

    public Data(long idnote, String time, String version) {
        this.idnote = idnote;
        this.time = time;
        this.version = version;
    }
}
