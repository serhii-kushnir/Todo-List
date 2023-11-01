package com.todolist.service;

import com.todolist.entity.Note;

import java.util.List;

public class NoteCrudServiceImpl implements NoteService {
    NoteService noteService;

    public NoteCrudServiceImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public List<Note> listAll() {
        return noteService.listAll();
    }

    @Override
    public Note add(Note note) {
        return noteService.add(note);
    }

    @Override
    public void deleteById(Long id) {
        noteService.deleteById(id);
    }

    @Override
    public void update(Note note) {
        noteService.update(note);
    }

    @Override
    public Note getById(Long id) {
        return noteService.getById(id);
    }
}
