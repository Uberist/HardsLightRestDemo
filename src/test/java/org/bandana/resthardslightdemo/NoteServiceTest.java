package org.bandana.resthardslightdemo;
import org.bandana.resthardslightdemo.db.entity.note.Note;
import org.bandana.resthardslightdemo.db.entity.Users;
import org.bandana.resthardslightdemo.request.IdsReq;
import org.bandana.resthardslightdemo.request.UserIdReq;
import org.bandana.resthardslightdemo.request.note.Block;
import org.bandana.resthardslightdemo.request.note.CreateNoteReq;
import org.bandana.resthardslightdemo.request.note.DataNoteReq;
import org.bandana.resthardslightdemo.request.note.DataReq;
import org.bandana.resthardslightdemo.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class NoteServiceTest {
    @Autowired
    NoteService noteService;
    @Test
    public void addNote(){
        List<Users> users = new ArrayList<Users>();
        users.add(new Users(1L, "John", "Doe"));
        users.add(new Users(3L, "Jane", "Doe"));

        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(new DataReq("texttext"), "2310414", "typetype"));
        blocks.add(new Block(new DataReq("fdffsfsd"), "fsdafaf", "dfjafdf"));

        DataNoteReq dataNoteReq = new DataNoteReq(
            blocks,
            "lflflfl",
            "fdfdfdffd"
        );

        CreateNoteReq createNoteReq = new CreateNoteReq(
                new Users(3L, "name", "password"),
                users,
                dataNoteReq,
                true,
                false,
                "2025-03-22 12:15",
                "2025-03-24 12:15",
                "2025-03-22 12:15"
        );

        boolean result = noteService.addNote(createNoteReq);
        assertEquals(true, result);
    }

    @Test
    public void getNote(){
        List<Note> result = noteService.getNote(new UserIdReq(1));
        List<Note> expected = new ArrayList<>();
        expected.add(new Note());
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void getNewNote(){
        List<Note> result = noteService.getNewNote(new UserIdReq(1));
        List<Note> expected = new ArrayList<>();
        expected.add(new Note());
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void deleteNotes(){
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        IdsReq req =  new IdsReq(ids);
        assertEquals(true, noteService.deleteNotes(req));
    }
}
