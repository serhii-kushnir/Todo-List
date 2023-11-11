//package com.todolist.service;
//
//import com.todolist.entity.Note;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Service
//public final class NoteServiceImplList implements NoteService {
//    private final List<Note> notes = new ArrayList<>();
//    private final Random random = new Random();
//
//    @Override
//    public List<Note> listAll() {
//        return notes;
//    }
//
//    @Override
//    public void add(final Note note) {
//        note.setId(random.nextLong(1, 20));
//        notes.add(note);
//    }
//
//    @Override
//    public void deleteById(final Long id) {
//        Note note = getById(id);
//        if (note != null) {
//            notes.remove(note);
//        } else {
//            throw new NoteNotFoundException("Note not delete with id: " + id);
//        }
//    }
//
//    @Override
//    public void update(final Note note) {
//        Note existingNote = getById(note.getId());
//        if(existingNote != null){
//            existingNote.setTitle(note.getTitle());
//            existingNote.setContent(note.getContent());
//        } else {
//            throw new NoteNotFoundException("Note not update with id: " + note.getId());
//        }
//    }
//
//    @Override
//    public Note getById(final Long id) {
//        for (Note note : notes) {
//            if (note.getId().equals(id)) {
//                return note;
//            }
//        }
//
//        throw new NoteNotFoundException("Note not found with id: " + id);
//    }
//}
