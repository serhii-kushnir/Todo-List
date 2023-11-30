package com.todolist.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.todolist.entity.Note;
import com.todolist.service.NoteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NoteRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteRestController noteRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteRestController).build();
    }

    @Test
    void getAllNotes() {
        List<Note> mockNotes = Collections.singletonList(new Note());
        when(noteService.listAll()).thenReturn(mockNotes);

        List<Note> result = noteRestController.getAllNotes();

        assertEquals(mockNotes, result);

        verify(noteService, times(1)).listAll();

        verifyNoMoreInteractions(noteService);
    }

    @Test
    void searchNoteById() {
        Long noteId = 1L;
        Note mockNote = new Note();
        when(noteService.findById(noteId)).thenReturn(mockNote);

        Note result = noteRestController.searchNoteById(noteId);

        assertEquals(mockNote, result);

        verify(noteService, times(1)).findById(noteId);

        verifyNoMoreInteractions(noteService);
    }

    @Test
    void deleteNoteById() throws Exception {
        Long noteId = 1L;

        mockMvc.perform(post("/api/note/delete/{id}", noteId))
                .andExpect(status().isOk());

        verify(noteService, times(1)).deleteById(noteId);

        verifyNoMoreInteractions(noteService);
    }

    @Test
    void postEditNoteById() throws Exception {
        Long noteId = 1L;
        Note existingNote = mock(Note.class); // Mock the Note class
        Note updatedNote = new Note();
        when(noteService.findById(noteId)).thenReturn(existingNote);

        mockMvc.perform(post("/api/note/edit/{id}", noteId)
                        .content(asJsonString(updatedNote))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(noteService, times(1)).findById(noteId);

        verify(existingNote, times(1)).setTitle(updatedNote.getTitle());
        verify(existingNote, times(1)).setContent(updatedNote.getContent());

        verify(noteService, times(1)).saveAndUpdate(existingNote);

        verifyNoMoreInteractions(noteService, existingNote);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
