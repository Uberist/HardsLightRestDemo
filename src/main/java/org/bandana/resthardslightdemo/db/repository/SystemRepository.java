package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.System;
import org.bandana.resthardslightdemo.db.entity.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends CrudRepository<System, Long> {

}
