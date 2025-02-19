package org.bandana.resthardslightdemo.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "users")
@Entity

public class Users {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_password")
    private String password;

    public Users(String password, String name) {
        this.password = password;
        this.name = name;
    }
}
