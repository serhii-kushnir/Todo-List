package com.todolist.service;

import com.todolist.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class NoteServiceImpl implements NoteService {
    private final List<Note> notes = new ArrayList<>();
    private final Random random = new Random();
    private static final String ERROR_MESSAGE = "Note not found with id: ";

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(notes);
    }

    @Override
    public Note add(final Note note) {
        note.setId(random.nextLong());
        notes.add(note);
        return note;
    }

    @Override
    public void deleteById(final Long id) {
        Note noteToRemove = null;
        for (Note note : notes) {
            if (Objects.equals(note.getId(), id)) {
                noteToRemove = note;
                break;
            }
        }

        if (noteToRemove != null) {
            notes.remove(noteToRemove);
        } else {
            throw new NoteNotFoundException(ERROR_MESSAGE + id);
        }
    }

    @Override
    public void update(final Note note) {
        for (Note existingNote : notes) {
            if (Objects.equals(existingNote.getId(), note.getId())) {
                existingNote.setTitle(note.getTitle());
                existingNote.setContent(note.getContent());
            } else {
                throw new NoteNotFoundException(ERROR_MESSAGE + note.getId());
            }
        }
    }

    @Override
    public Note getById(final Long id) {
        for (Note note : notes) {
            if (Objects.equals(note.getId(), id)) {
                return note;
            }
        }

        throw new NoteNotFoundException(ERROR_MESSAGE + id);
    }
}
