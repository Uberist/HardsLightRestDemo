package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.credentials.CredUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsUserRepository extends CrudRepository<CredUser, Long> {
    @Modifying
    @Query(value = "select cred_id from hards.credentials_user where user_id = :user_id;", nativeQuery = true)
    public Iterable<Long> findCredIdByUserId(@Param("user_id") Long userId);
    @Modifying
    @Query(value = "update hards.credentials_user set cred_id = null, user_id = null " +
            "where hards.credentials_user.cred_id = :cred_id ", nativeQuery = true)
    public void updateCredByCredId(@Param("cred_id") Long credId);
    @Modifying
    @Query(value = "select user_id from hards.credentials_user where cred_id = :cred_id", nativeQuery = true)
    public Iterable<Long> findUserIdByCredId(@Param("cred_id") Long credId);
}