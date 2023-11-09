package com.todolist.service;

import com.todolist.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> listAll();

    void add(final Note note);

    void deleteById(final Long id);

    void update(final Note note);

    Note getById(final Long id);
}
