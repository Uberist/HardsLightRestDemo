package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.note.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteUserRepository extends JpaRepository<NoteUser, Long> {
}
