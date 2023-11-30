package com.todolist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyNoInteractions;

import com.todolist.entity.Note;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        NoteRepository noteRepository = mock(NoteRepository.class);
        when(noteRepository.findAll()).thenReturn(Arrays.asList(new Note(), new Note()));
        NoteService noteService = new NoteService(noteRepository);

        List<Note> result = noteService.listAll();

        assertEquals(2, result.size());
    }

    @Test
    void testDeleteById() {
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        Long id = 1L;

        noteService.deleteById(id);

        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById() {
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        Long id = 1L;
        when(noteRepository.findById(id)).thenReturn(Optional.of(new Note()));

        Note result = noteService.findById(id);

        assertNotNull(result);
    }

    @Test
    void testSearchNotes() {
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        String search = "keyword";
        when(noteRepository.searchNotes(search)).thenReturn(Arrays.asList(new Note(), new Note()));

        List<Note> result = noteService.searchNotes(search);

        assertEquals(2, result.size());
    }


    @Test
    void saveAndUpdate_ValidNote() {
        Note validNote = new Note();
        validNote.setTitle("Valid Title");
        validNote.setContent("Valid Content");

        noteService.saveAndUpdate(validNote);

        verify(noteRepository, times(1)).save(validNote);

        verifyNoMoreInteractions(noteRepository);
    }

    @Test
    void saveAndUpdate_NullNote() {
        assertThrows(IllegalArgumentException.class, () -> noteService.saveAndUpdate(null));

        verifyNoInteractions(noteRepository);
    }

    @Test
    void saveAndUpdate_InvalidTitleLength() {
        Note invalidTitleNote = new Note();
        invalidTitleNote.setTitle(""); // Invalid title length

        assertThrows(IllegalArgumentException.class, () -> noteService.saveAndUpdate(invalidTitleNote));

        verifyNoInteractions(noteRepository);
    }

    @Test
    void saveAndUpdate_InvalidContentLength() {
        Note invalidContentNote = new Note();
        invalidContentNote.setTitle("Valid Title");
        invalidContentNote.setContent(""); // Invalid content length

        assertThrows(IllegalArgumentException.class, () -> noteService.saveAndUpdate(invalidContentNote));

        verifyNoInteractions(noteRepository);
    }
}