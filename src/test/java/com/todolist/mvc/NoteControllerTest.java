package com.todolist.mvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.todolist.entity.Note;
import com.todolist.service.NoteService;

import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

class NoteControllerTest {

    @Test
    void testGetAllNotes() throws Exception {
        // Arrange
        NoteService noteService = mock(NoteService.class);
        NoteController controller = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(controller).build();
        List<Note> notes = Arrays.asList(new Note(), new Note());
        when(noteService.listAll()).thenReturn(notes);

        // Act & Assert
        mockMvc.perform(get("/note/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("notes"))
                .andExpect(view().name("notes-list"));
    }

    @Test
    void testCreateNote() throws Exception {
        // Arrange
        NoteService noteService = mock(NoteService.class);
        NoteController controller = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(controller).build();

        // Act & Assert
        mockMvc.perform(get("/note/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("note"))
                .andExpect(view().name("create-note"));
    }

    @Test
    void testCreatingNote() throws Exception {
        // Arrange
        NoteService noteService = mock(NoteService.class);
        NoteController controller = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(controller).build();

        // Act & Assert
        mockMvc.perform(post("/note/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }

    @Test
    void testEditNoteById() throws Exception {
        // Arrange
        Long id = 1L;
        NoteService noteService = mock(NoteService.class);
        NoteController controller = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(controller).build();
        when(noteService.findById(id)).thenReturn(new Note());

        // Act & Assert
        mockMvc.perform(get("/note/edit?id=" + id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("note"))
                .andExpect(view().name("edit-note"));
    }

    @Test
    void testSaveEditingNoteById() throws Exception {
        // Arrange
        NoteService noteService = mock(NoteService.class);
        NoteController controller = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(controller).build();

        // Act & Assert
        mockMvc.perform(post("/note/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }

    @Test
    void testDeleteNoteById() throws Exception {
        // Arrange
        long id = 1L;
        NoteService noteService = mock(NoteService.class);
        NoteController controller = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(controller).build();

        // Act & Assert
        mockMvc.perform(get("/note/delete?id=" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }
}