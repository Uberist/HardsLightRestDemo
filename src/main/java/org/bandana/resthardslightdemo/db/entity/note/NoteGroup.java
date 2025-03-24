package org.bandana.resthardslightdemo.db.entity.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "note_group")
@Entity
public class NoteGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "id_note")
    private Long idnote;
    @Column(name = "id_group")
    private Long idgroup;
}
