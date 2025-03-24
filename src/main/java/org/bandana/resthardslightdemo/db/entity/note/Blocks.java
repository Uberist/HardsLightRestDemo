package org.bandana.resthardslightdemo.db.entity.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "blocks")
@Entity
public class Blocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idblock")
    private long idblock;
    @Column(name = "iddata")
    private long iddata;
    @Column(name = "data")
    private String data;
    @Column(name = "id")
    private String id;
    @Column(name = "type")
    private String type;

    public Blocks(long iddata, String data, String id, String type) {
        this.iddata = iddata;
        this.data = data;
        this.id = id;
        this.type = type;
    }
}
