package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.note.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {

}
