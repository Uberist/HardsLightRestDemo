package org.bandana.resthardslightdemo.db.entity.credentials;

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
    @Column(name = "accept")
    private Boolean accept;
    @Column(name = "reject")
    private Boolean reject;

    public CredUser(Long credid, Long userid, Boolean accept, Boolean reject) {
        this.credid = credid;
        this.userid = userid;
        this.accept = accept;
        this.reject = reject;
    }
    public CredUser(Long credid, Long userid) {
        this.credid = credid;
        this.userid = userid;
    }
}
