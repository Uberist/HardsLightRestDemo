package org.bandana.resthardslightdemo.db.entity.credentials;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "hards", name = "credentials_system")
public class CredSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "cred_id")
    Long cred_id;
    @Column(name = "system_id")
    Long system_id;

    public CredSystem(Long cred_id, Long system_id) {
        this.cred_id = cred_id;
        this.system_id = system_id;
    }
}
