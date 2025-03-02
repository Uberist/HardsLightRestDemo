package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.Credentials;
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

    @Query(value =
            "select hards.credentials.id, hards.credentials.user_id, hards.credentials.c_username, hards.credentials.c_login, hards.credentials.c_password, hards.credentials.group_id, hards.credentials.markbook " +
                    "from hards.credentials join hards.credentials_user on " +
                    "hards.credentials.id = hards.credentials_user.cred_id " +
                    "where hards.credentials_user.user_id = :userid and markbook = true;", nativeQuery = true)
    Iterable<Credentials> findByUserIdAndMarkbook(@Param("userid") Long userid);
}
