package org.bandana.resthardslightdemo.service;

import jakarta.transaction.Transactional;
import org.bandana.resthardslightdemo.db.entity.*;
import org.bandana.resthardslightdemo.db.entity.note.Blocks;
import org.bandana.resthardslightdemo.db.entity.note.Data;
import org.bandana.resthardslightdemo.db.entity.note.Note;
import org.bandana.resthardslightdemo.db.entity.note.NoteUser;
import org.bandana.resthardslightdemo.db.repository.BlocksRepository;
import org.bandana.resthardslightdemo.db.repository.DataRepository;
import org.bandana.resthardslightdemo.db.repository.NoteRepository;
import org.bandana.resthardslightdemo.db.repository.NoteUserRepository;
import org.bandana.resthardslightdemo.request.AcceptAndRejectReq;
import org.bandana.resthardslightdemo.request.IdsReq;
import org.bandana.resthardslightdemo.request.UserIdReq;
import org.bandana.resthardslightdemo.request.note.Block;
import org.bandana.resthardslightdemo.request.note.CreateNoteReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private BlocksRepository blocksRepository;
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private NoteUserRepository noteUserRepository;
    public boolean addNote(CreateNoteReq note) {
        Note noteEntity = noteRepository.save(new Note(
                        note.getSender().getId(),
                        Timestamp.valueOf(note.getStartaccess().replace('T', ' ') + ":00"),
                        Timestamp.valueOf(note.getEndaccess().replace('T', ' ') + ":00"),
                        Timestamp.valueOf(note.getTimesend().replace('T', ' ') + ":00")));
        Data dataEntity =
                dataRepository.save(
                        new Data(noteEntity.getId(),
                                note.getData().getTime(),
                                note.getData().getVersion()));
        for (Block block : note.getData().getBlocks())
            blocksRepository.save(
                    new Blocks(dataEntity.getId(),
                            block.getData().getText(),
                            block.getId(),
                            block.getType()));
        for(Users user : note.getUsersids())
            noteUserRepository.save(new NoteUser(user.getId(), noteEntity.getId()));
        return noteEntity.getId() != 0;
    }

    public boolean acceptNote(AcceptAndRejectReq req){
        noteRepository.accept(req.getCredid(), req.getUserid());
        return true;
    }

    public boolean rejectNote(AcceptAndRejectReq req){
        noteRepository.reject(req.getCredid(), req.getUserid());
        return true;
    }

    public List<Note> getNewNote(UserIdReq req){
        return noteRepository.getNewNote(Long.valueOf(req.getUserid()));
    }

    public List<Note> getNote(UserIdReq req){
        return noteRepository.getNote(Long.valueOf(req.getUserid()));
    }
    @Transactional
    public boolean deleteNotes(IdsReq req){
        Iterable<Long> ids = req.getIds();
        // Проверяем, существуют ли записи перед удалением
        Iterable<Note> existingNotes = noteRepository.findAllById(ids);
        if (!existingNotes.iterator().hasNext()) {
            return false; // Если записей нет, возвращаем false
        }
        noteRepository.deleteAllById(ids);
        // Проверяем, что удаление сработало
        Iterable<Note> result = noteRepository.findAllById(ids);
        return !result.iterator().hasNext();
    }

}
