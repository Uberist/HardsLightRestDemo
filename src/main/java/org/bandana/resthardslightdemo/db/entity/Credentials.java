package org.bandana.resthardslightdemo.db.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


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
    @Column(name = "begin_date_access")
    private Timestamp begindateaccess;
    @Column(name = "end_date_access")
    private Timestamp enddateaccess;
    @Column(name = "time_send")
    private Timestamp timesend;
    @Nullable
    @Column(name = "accept")
    private Boolean accept;
    @Nullable
    @Column(name = "reject")
    private Boolean reject;

    public Credentials(
            String login,
            String password,
            Long sender,
            Timestamp begindateaccess,
            Timestamp enddateaccess,
            Timestamp timesend,
            boolean accept,
            boolean reject) {
        this.login = login;
        this.password = password;
        this.sender = sender;
        this.begindateaccess = begindateaccess;
        this.enddateaccess = enddateaccess;
        this.timesend = timesend;
        this.accept = accept;
        this.reject = reject;
    }

    public Credentials(String login, String password, Long sender) {
        this.login = login;
        this.password = password;
        this.sender = sender;
    }
}
