package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.note.Blocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocksRepository extends JpaRepository<Blocks, Long> {

}
