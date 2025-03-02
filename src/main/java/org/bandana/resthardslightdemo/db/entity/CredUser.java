package org.bandana.resthardslightdemo.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "credentials_user")
@Entity
public class CredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cred_id")
    private Long credid;
    @Column(name = "user_id")
    private Long userid;

    public CredUser(Long credid, Long userid) {
        this.credid = credid;
        this.userid = userid;
    }
}
