package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.credentials.System;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends CrudRepository<System, Long> {

}
