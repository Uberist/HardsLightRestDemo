package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
    Iterable<Credentials> findByMarkbook(Boolean markbook);

    Iterable<Credentials> findByGroupId(Long groupId);
}
