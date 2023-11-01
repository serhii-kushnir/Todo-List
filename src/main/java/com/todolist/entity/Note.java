package com.todolist.entity;

import lombok.Data;

@Data
public class Note {
    private Long id;
    private String title;
    private String content;
}
