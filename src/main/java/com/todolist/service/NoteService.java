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
        if (note != null) {
            noteRepository.save(note);
        } else {
            throw new IllegalArgumentException("Note cannot be null");
        }
    }

    public void deleteById(final Long id) {
        if (id != null) {
            noteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public Note findById(final Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public List<Note> searchNotes(final String search) {
        return noteRepository.searchNotes(search);
    }
}
