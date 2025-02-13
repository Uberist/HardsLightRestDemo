package org.bandana.resthardslightdemo.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hards", name = "credentials_group")
@Entity
public class GroupsCred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name_group")
    private String groupname;

    public GroupsCred(String groupName) {
        this.groupname = groupName;
    }
}
