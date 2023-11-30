package com.todolist.service;

import com.todolist.entity.Note;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public final class NoteService {
    private final NoteRepository noteRepository;
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 255;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }


    public void saveAndUpdate(final Note note) {
        if (note == null) {
            exception("Note cannot be null");
        }

        if (note.getTitle().length() > MAX_TITLE_LENGTH || note.getTitle().isEmpty()) {
            exception("InvalidTitleLength");
        }

        if (note.getContent().length() > MAX_CONTENT_LENGTH || note.getContent().isEmpty()) {
            exception("InvalidContentLength");
        } else {
            noteRepository.save(note);
        }
    }

    public void deleteById(final Long id) {
        if (id != null) {
            noteRepository.deleteById(id);
        } else {
            exception("ID cannot be null");
        }
    }

    public Note findById(final Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public List<Note> searchNotes(final String search) {
        return noteRepository.searchNotes(search);
    }

    private static void exception(final String msg) {
        throw new IllegalArgumentException(msg);
    }
}
