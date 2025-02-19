package org.bandana.resthardslightdemo.db.repository;

import jakarta.transaction.Transactional;
import org.bandana.resthardslightdemo.db.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
