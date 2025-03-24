package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.credentials.CredSystem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CredSystemRepository extends CrudRepository<CredSystem, Long> {
    @Query(value = "select system_id from hards.credentials_system where cred_id = :cred_id", nativeQuery = true)
    public Iterable<Long> findByCredId(@Param("cred_id")Long cred_id);
    @Modifying
    @Query(value = "update hards.credentials_system set cred_id = null , system_id = null where cred_id = :cred_id", nativeQuery = true)
    public void updateByCred_id(@Param("cred_id")Long cred_id);

}
