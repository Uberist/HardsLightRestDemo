package org.bandana.resthardslightdemo.db.repository;

import jakarta.transaction.Transactional;
import org.bandana.resthardslightdemo.db.entity.Credentials;
import org.bandana.resthardslightdemo.db.entity.GroupsCred;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GroupsCredRepository extends CrudRepository<GroupsCred, Long> {
}
