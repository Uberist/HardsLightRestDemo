package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
