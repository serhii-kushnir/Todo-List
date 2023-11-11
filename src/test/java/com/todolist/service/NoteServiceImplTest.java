//package com.todolist.service;
//
//import com.todolist.entity.Note;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class NoteServiceImplTest {
//
//    private NoteServiceImpl noteService;
//
//    @BeforeEach
//    void setUp() {
//        noteService = new NoteServiceImpl();
//    }
//
//    @Test
//    void testAddNote() {
//        NoteServiceImpl noteService = new NoteServiceImpl();
//
//        Note note = new Note();
//        note.setTitle("Title");
//        note.setContent("Content");
//
//        noteService.add(note);
//
//        assertTrue(noteService.listAll().contains(note));
//    }
//
//    @Test
//    void testUpdateNote() {
//        Note note = new Note();
//        note.setTitle("Title");
//        note.setContent("Content");
//        noteService.add(note);
//
//        Note updatedNote = new Note();
//        note.setTitle("Updated Title");
//        note.setContent("Updated Content");
//        updatedNote.setId(note.getId());
//
//        noteService.update(updatedNote);
//
//        assertEquals(updatedNote.getTitle(), noteService.getById(note.getId()).getTitle());
//        assertEquals(updatedNote.getContent(), noteService.getById(note.getId()).getContent());
//    }
//
//    @Test
//    void testUpdateNoteNotFound() {
//        Note nonExistingNote = new Note();
//        nonExistingNote.setTitle("Non-existing Title");
//        nonExistingNote.setContent("Non-existing Content");
//        nonExistingNote.setId(999L);
//
//        assertThrows(NoteNotFoundException.class, () -> {
//            noteService.update(nonExistingNote);
//        });
//    }
//
//    @Test
//    void testDeleteNote() {
//        Note note = new Note();
//        note.setTitle("Title");
//        note.setContent("Content");
//        noteService.add(note);
//
//        noteService.deleteById(note.getId());
//
//        assertFalse(noteService.listAll().contains(note));
//    }
//
//    @Test
//    void testDeleteNoteWithInvalidId() {
//        assertThrows(NoteNotFoundException.class, () -> {
//            noteService.deleteById(999L);
//        });
//    }
//
//    @Test
//    void testGetNoteById() {
//        Note note = new Note();
//        note.setTitle("Title");
//        note.setContent("Content");
//        noteService.add(note);
//
//        Note retrievedNote = noteService.getById(note.getId());
//
//        assertEquals(note, retrievedNote);
//    }
//
//    @Test
//    void testGetNoteByIdNotFound() {
//        assertThrows(NoteNotFoundException.class, () -> {
//            noteService.getById(999L);
//        });
//    }
//}
