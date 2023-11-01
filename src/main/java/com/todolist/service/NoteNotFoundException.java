package com.todolist.service;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(final String msg) {
        super(msg);
    }
}
