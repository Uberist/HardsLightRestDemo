package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
    Iterable<Credentials> findByMarkbook(Boolean markbook);

    @Query(value = "select distinct credentials.c_system from hards.credentials where user_id = :userid;", nativeQuery = true)
    Iterable<String> findSystemByUserId(@Param("userid") Long userid);

    @Query(
            value = "select * from hards.credentials where now() > begin_date_access and " +
                    "now() < end_date_access and " +
                    "now() > time_send and " +
                    "id in (select cred_id from hards.credentials_user where user_id = :user_id) and " +
                    "accept = true;", nativeQuery = true)
    Iterable<Credentials> findAllCredByUserId(@Param("user_id") Long userid);

    @Query(
            value = "select * from hards.credentials where now() > begin_date_access and " +
                    "now() < end_date_access and " +
                    "now() > time_send and " +
                    "id in (select cred_id from hards.credentials_user where user_id = :user_id) and " +
                    "markbook = true and " +
                    "accept = true;", nativeQuery = true)
    Iterable<Credentials> findByUserIdAndMarkbook(@Param("user_id") Long userid);

    @Query(
            value = "select * from hards.credentials where now() > begin_date_access and " +
                    "now() < end_date_access and " +
                    "now() > time_send and " +
                    "id in (select cred_id from hards.credentials_user where user_id = :user_id) and " +
                    "accept = false and " +
                    "reject = false;", nativeQuery = true)
    Iterable<Credentials> findNewCredentialsByUserId(@Param("user_id") Long userid);

    @Modifying
    @Query(
            value = "update hards.credentials set accept = true where id = :cred_id",
            nativeQuery = true)
    void accept(@Param("cred_id") Long cred_id);
    @Modifying
    @Query(
            value = "update hards.credentials set reject = true where id = :cred_id",
            nativeQuery = true)
    void reject(@Param("cred_id") Long cred_id);

}
