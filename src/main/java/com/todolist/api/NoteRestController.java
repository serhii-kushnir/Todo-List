package com.todolist.api;

import com.todolist.entity.Note;
import com.todolist.service.NoteService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("api/note/")
@RequiredArgsConstructor
public final class NoteRestController {
    private final NoteService noteService;

    @GetMapping("/list")
    public List<Note> getAllNotes() {
        return noteService.listAll();
    }

    @GetMapping("/id/{id}")
    public Note searchNoteById(final @PathVariable Long id) {
        return noteService.findById(id);
    }


    @PostMapping("/create")
    public void createNote(final @RequestBody Note note) {
        noteService.saveAndUpdate(note);
    }

    @GetMapping("/edit/{id}")
    public Note editNoteById(final @PathVariable Long id) {
        return noteService.findById(id);
    }

    @PostMapping("/edit/{id}")
    public void postEditNoteById(final @PathVariable Long id, final @RequestBody Note updatedNote) {
        Note existingNote = noteService.findById(id);
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());

        noteService.saveAndUpdate(existingNote);
    }

    @PostMapping("/delete/{id}")
    public void deleteNoteById(final @PathVariable Long id) {
        noteService.deleteById(id);
    }
}
