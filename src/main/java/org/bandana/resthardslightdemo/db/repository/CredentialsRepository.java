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

    Iterable<Credentials> findByGroupId(Long groupId);

    Iterable<Credentials> findByUserId(Long userId);

    @Query(value = "select distinct credentials.c_system from hards.credentials where user_id = :userid;", nativeQuery = true)
    Iterable<String> findSystemByUserId(@Param("userid") Long userid);

    @Query(value = "select * from hards.credentials where user_id = :userid and markbook = true;", nativeQuery = true)
    Iterable<Credentials> findByUserIdAndMarkbook(@Param("userid") Long userid);
}
