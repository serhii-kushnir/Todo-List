package com.todolist.service;

final class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(final String msg) {
        super(msg);
    }
}
