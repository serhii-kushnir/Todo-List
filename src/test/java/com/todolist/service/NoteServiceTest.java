package com.todolist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.todolist.entity.Note;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class NoteServiceTest {

    @Test
    void testListAll() {
        // Arrange
        NoteRepository noteRepository = mock(NoteRepository.class);
        when(noteRepository.findAll()).thenReturn(Arrays.asList(new Note(), new Note()));
        NoteService noteService = new NoteService(noteRepository);

        // Act
        List<Note> result = noteService.listAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testSaveAndUpdate() {
        // Arrange
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        Note note = new Note();

        // Act
        noteService.saveAndUpdate(note);

        // Assert
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testDeleteById() {
        // Arrange
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        Long id = 1L;

        // Act
        noteService.deleteById(id);

        // Assert
        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById() {
        // Arrange
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        Long id = 1L;
        when(noteRepository.findById(id)).thenReturn(Optional.of(new Note()));

        // Act
        Note result = noteService.findById(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testSearchNotes() {
        // Arrange
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);
        String search = "keyword";
        when(noteRepository.searchNotes(search)).thenReturn(Arrays.asList(new Note(), new Note()));

        // Act
        List<Note> result = noteService.searchNotes(search);

        // Assert
        assertEquals(2, result.size());
    }
}