package com.todolist.service;

import com.todolist.entity.Note;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public final class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public void saveAndUpdate(final Note note) {
        noteRepository.save(note);
    }

    public void deleteById(final Long id) {
        noteRepository.deleteById(id);
    }

    public Note findById(final Long id) {
        return noteRepository.findById(id).orElse(null);
    }
}
