package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.credentials.CredGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupCredRepositoryToMany extends JpaRepository<CredGroup, Long> {
    @Query(value = "select hards.cred_group.id_group from hards.cred_group " +
            "join hards.credentials on hards.cred_group.id_cred = hards.credentials.id " +
            "join hards.credentials_group on hards.cred_group.id_group = hards.credentials_group.id " +
            "join hards.credentials_user on hards.credentials.id = hards.credentials_user.cred_id " +
            "where hards.credentials_user.user_id = :id_user;",
            nativeQuery = true)
    public List<Long> findIdsGroupById_user(@Param("id_user") Long id_user);
    @Query(value = "select hards.cred_group.id from hards.cred_group " +
            "join hards.credentials on hards.cred_group.id_cred = hards.credentials.id " +
            "join hards.credentials_group on hards.cred_group.id_group = hards.credentials_group.id " +
            "where hards.credentials_group.id = :id_cred;",
            nativeQuery = true)
    public List<Long> findIdsGroup_CredById_Cred(@Param("id_cred") Long id_cred);
    @Modifying
    @Query(value = "update hards.cred_group set id_cred = null, id_group = null where id = :id_group_cred;", nativeQuery = true)
    public void updateIdGroup(@Param("id_group_cred") Long id_group_cred);

    @Modifying
    @Query(value = "update hards.cred_group set id_cred = null, id_group = null where id_cred = :id_cred;", nativeQuery = true)
    public void updateIdCred(@Param("id_cred") Long id_cred);

    @Modifying
    @Query(value = "select id_cred from hards.cred_group where id_group = :idgroup;" ,nativeQuery = true)
    public List<Long> findIdCredByIdgroup(@Param("idgroup") Long idgroup);

}
