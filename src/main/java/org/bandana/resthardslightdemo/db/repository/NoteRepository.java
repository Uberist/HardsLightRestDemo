package org.bandana.resthardslightdemo.db.repository;

import org.bandana.resthardslightdemo.db.entity.note.Note;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    @Modifying
    @Query(value = "select * from hards.note where " +
            "now() > begin_date_access and " +
            "now() < end_date_access and " +
            "now() > time_send and " +
            "id in (select id_note from hards.note_user where id_user = :id_user and accept is null and reject is null);",
    nativeQuery = true)
    List<Note> getNewNote(@Param("id_user") Long id_user);

    @Modifying
    @Query(value = "select * from hards.note where " +
            "now() > begin_date_access and " +
            "now() < end_date_access and " +
            "now() > time_send and " +
            "id in (select id_note from hards.note_user where id_user = :id_user and accept = true);",
            nativeQuery = true)
    List<Note> getNote(@Param("id_user") Long id_user);

    @Modifying
    @Query(
            value = "update hards.note_user set accept = true where id_note = :note_id and id_user = :user_id",
            nativeQuery = true)
    void accept(@Param("note_id") Long note_id, @Param("user_id") Long user_id);
    @Modifying
    @Query(
            value = "update hards.note_user set reject = true where id_note = :note_id and id_user = :user_id",
            nativeQuery = true)
    void reject(@Param("note_id") Long note_id, @Param("user_id") Long user_id);
}
