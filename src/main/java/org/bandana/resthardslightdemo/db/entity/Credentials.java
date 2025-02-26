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
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "c_username")
    private String username;
    @Column(name = "c_login")
    private String login;
    @Column(name = "c_password")
    private String password;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "markbook")
    private Boolean markbook;

    public Credentials(Long userId, String username, String login, String password) {
        this.userId = userId;
        this.username = username;
        this.login = login;
        this.password = password;
    }
}
