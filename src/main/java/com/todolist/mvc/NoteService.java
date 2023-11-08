//package com.todolist.mvc;
//
//public class NoteService {
//    private final List<Note> noteList = new ArrayList<>();
//
//    public List<Note> listAll() {
//        return noteList;
//    }
//
//    public Note add(Note note) {
//        Long id = generatorId();
//        note.setId(id);
//        noteList.add(note);
//        return note;
//    }
//
//    public Note getById(long id) {
//        for (Note note : noteList) {
//            if (note.getId() == id) {
//                return note;
//            }
//        }
//       throw new IllegalArgumentException();
//    }
//
//    public void deleteById(long id) {
//        Note note = getById(id);
//        if (note == null) {
//            throw new IllegalArgumentException();
//        } else {
//            noteList.remove(note);
//        }
//    }
//
//    public void update(Note note){
//        Note existingNote = getById(note.getId());
//        if(existingNote == null){
//            throw new IllegalArgumentException();
//        } else {
//            existingNote.setTitle(note.getTitle());
//            existingNote.setContent(note.getContent());
//        }
//    }
//
//    private Long generatorId(){
//        int index = noteList.size()-1;
//        return noteList.get(index).getId() + 1;
//    }
//}
//0 comments on commit 2335ed2