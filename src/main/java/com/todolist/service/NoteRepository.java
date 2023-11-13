package com.todolist.service;

import com.todolist.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(n.content) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Note> searchNotes(String search);
}
