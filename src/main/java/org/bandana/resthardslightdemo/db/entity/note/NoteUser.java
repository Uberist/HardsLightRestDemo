package org.bandana.resthardslightdemo.db.entity.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "note_user")
@Entity
public class NoteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "id_user")
    private long iduser;
    @Column(name = "id_note")
    private long idnote;

    public NoteUser(long iduser, long idnote) {
        this.iduser = iduser;
        this.idnote = idnote;
    }
}
