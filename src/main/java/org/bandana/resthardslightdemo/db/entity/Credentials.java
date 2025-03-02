package org.bandana.resthardslightdemo.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "credentials")
@Entity
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "sender")
    private Long sender;
    @Column(name = "markbook")
    private Boolean markbook;

    public Credentials(String login, String password, Long sender) {
        this.login = login;
        this.password = password;
        this.sender = sender;
    }
}
