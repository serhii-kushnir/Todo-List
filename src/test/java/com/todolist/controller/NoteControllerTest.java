package com.todolist.controller;

import com.todolist.entity.Note;
import com.todolist.service.NoteServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

    @Mock
    private NoteServiceImpl noteService;

    @Mock
    private Model model;

    @InjectMocks
    private NoteController noteController;

    @Test
    void testGetAllNotes() {
        when(noteService.listAll()).thenReturn(someListOfNotes());

        String viewName = noteController.getAllNotes(model);

        assertEquals("notes", viewName);
        verify(model).addAttribute("notes", someListOfNotes());
    }

    @Test
    void testCreateNote() {
        String viewName = noteController.createNote(model);

        assertEquals("create-note", viewName);
        verify(model).addAttribute("note", new Note());
    }

    @Test
    void testSaveCreatingNote() {
        Note note = someNote();

        String viewName = noteController.saveCreatingNote(note);

        assertEquals("redirect:/note/list", viewName);
        verify(noteService).add(note);
    }

    @Test
    void testEditNoteById() {
        Long noteId = 1L;
        Note note = someNote();
        when(noteService.getById(noteId)).thenReturn(note);

        String viewName = noteController.editNoteById(noteId, model);

        assertEquals("edit-note", viewName);
        verify(model).addAttribute("note", note);
    }

    @Test
    void testSaveEditingNoteById() {
        Note note = someNote();

        String viewName = noteController.saveEditingNoteById(note);

        assertEquals("redirect:/note/list", viewName);
        verify(noteService).update(note);
    }

    @Test
    void testDeleteNoteById() {
        Long noteId = 1L;

        String viewName = noteController.deleteNoteById(noteId);

        assertEquals("redirect:/note/list", viewName);
        verify(noteService).deleteById(noteId);
    }

    private Note someNote() {
        Note note = new Note();
        note.setTitle("Title");
        note.setContent("Content");

        return note;
    }

    private List<Note> someListOfNotes() {
        List<Note> notes = new ArrayList<>();

        Note note = new Note();
        note.setTitle("Title");
        note.setContent("Content");
        notes.add(note);

        Note note2 = new Note();
        note2.setTitle("Title2");
        note2.setContent("Content2");
        notes.add(note2);

        return notes;
    }
}