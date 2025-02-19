package org.bandana.resthardslightdemo.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DialectOverride;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "hards", name = "cred_group")
public class CredGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "id_cred")
    Long id_cred;
    @Column(name = "id_group")
    Long id_group;
}
